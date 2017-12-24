package catalin.coinnews;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import catalin.coinnews.adapters.CoinListAdapter;
import catalin.coinnews.database.DataSource;
import catalin.coinnews.models.Coin;
import catalin.coinnews.services.CoinListService;
import catalin.coinnews.services.CoinListServiceImpl;

public class MainActivity extends Activity {

    private static Context contextOfApplication;
    private Button getCoin;
    private ProgressDialog progressDialog;
    private ArrayList<Coin> cryptoCoins;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextOfApplication = getApplicationContext();
        recyclerView = findViewById(R.id.coinListRecycle);
        ButterKnife.bind(this);

        new getData().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        DataSource dataSource = new DataSource(getApplicationContext());
    }

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }

    class getData extends AsyncTask<String, Void, ArrayList<Coin>>  {

        protected void onPreExecute(){
            super.onPreExecute();
            if (progressDialog == null) {
                progressDialog = ProgressDialog.show(MainActivity.this, "Please wait.", "Retrieving data...",true );
            }
        }

        @Override
        protected ArrayList<Coin> doInBackground(String... strings) {
            CoinListService cls;
            Coin coin = null;
            ArrayList<Coin> coins = new ArrayList<>();

            cls = new CoinListServiceImpl();

            try {
                coins = cls.getCoins();
            } catch (IOException | NetworkErrorException |JSONException e) {
                e.printStackTrace();
            }
            return coins;
        }

        protected void onPostExecute(ArrayList<Coin> coins) {
            cryptoCoins = coins;

            CoinListAdapter adapter = new CoinListAdapter(contextOfApplication, cryptoCoins);

            recyclerView.setAdapter(adapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contextOfApplication);
            recyclerView.setLayoutManager(layoutManager);

//            recyclerView.setHasFixedSize(true); ONLY IF THE LIST IS A FIXED SIZED!

            for(Coin coin: coins) {
                DataSource dataSource = new DataSource(contextOfApplication);
                dataSource.create(coin);
            }


            if (progressDialog != null) {
                try {
                    progressDialog.dismiss();
                } catch (Exception ex) {}
                progressDialog = null;
            }
        }
    }
}
