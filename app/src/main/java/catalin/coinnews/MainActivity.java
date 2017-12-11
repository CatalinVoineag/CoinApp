package catalin.coinnews;

import android.accounts.NetworkErrorException;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import catalin.coinnews.models.Coin;
import catalin.coinnews.services.CoinService;
import catalin.coinnews.services.CoinServiceImpl;

public class MainActivity extends ListActivity {

    private static Context contextOfApplication;
    private Button getCoin;
    private ProgressDialog progressDialog;
    private ArrayList<Coin> cryptoCoins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextOfApplication = getApplicationContext();


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

            ArrayList<String> coinNames = new ArrayList<String>();

            for(int i=0; i < cryptoCoins.size(); i++) {
                coinNames.add(cryptoCoins.get(i).getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, coinNames);

            setListAdapter(adapter);

            if (progressDialog != null) {
                try {
                    progressDialog.dismiss();
                } catch (Exception ex) {}
                progressDialog = null;
            }
        }
    }
}
