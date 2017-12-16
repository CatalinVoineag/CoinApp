package catalin.coinnews;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import catalin.coinnews.adapters.CoinList;
import catalin.coinnews.models.Coin;
import catalin.coinnews.services.CoinService;
import catalin.coinnews.services.CoinServiceImpl;

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

        new getData().execute();

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
            CoinService cs;
            Coin coin = null;
            ArrayList<Coin> coins = new ArrayList<>();

            cs = new CoinServiceImpl();

            try {
                coins = cs.getCoins();
            } catch (IOException | NetworkErrorException |JSONException e) {
                e.printStackTrace();
            }
            return coins;
        }

        protected void onPostExecute(ArrayList<Coin> coins) {
            cryptoCoins = coins;

            CoinList adapter = new CoinList(contextOfApplication, cryptoCoins);

            recyclerView.setAdapter(adapter);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contextOfApplication);
            recyclerView.setLayoutManager(layoutManager);

//            recyclerView.setHasFixedSize(true); ONLY IF THE LIST IS A FIXED SIZED!


            if (progressDialog != null) {
                try {
                    progressDialog.dismiss();
                } catch (Exception ex) {}
                progressDialog = null;
            }
        }
    }
}
