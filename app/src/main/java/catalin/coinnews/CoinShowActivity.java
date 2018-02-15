package catalin.coinnews;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import catalin.coinnews.models.Coin;
import catalin.coinnews.services.CoinService;
import catalin.coinnews.services.CoinServiceImpl;

/**
 * Created by catalin on 17/12/17.
 */

public class CoinShowActivity extends Activity {

    private Context contextOfApplication;
    private String coinName;
    private Coin cryptoCoin;
    public static final String COIN = "coin";

    @BindView(R.id.coinName) TextView coinNameField;
    @BindView(R.id.coinPriceUSD) TextView coinPriceUSDField;
    @BindView(R.id.coinPriceBTC) TextView coinPriceBTCField;
    @BindView(R.id.holdings) Button holdings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);
        contextOfApplication = getApplicationContext();
        coinName = getIntent().getStringExtra("coinName");
        ButterKnife.bind(this);

        new getData().execute();

//        holdings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(CoinShowActivity.this, HoldingActivity.class);
//                ArrayList<Coin> coinArray = new ArrayList<Coin>();
//                coinArray.add(cryptoCoin);
//                intent.putParcelableArrayListExtra(COIN, coinArray);
//                startActivities(new Intent[]{intent});
//            }
//        });
    }

    class getData extends AsyncTask<String, Void, Coin> {

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            if(progressDialog == null){
                progressDialog = ProgressDialog.show(CoinShowActivity.this, "Please wait.", "Retrieving data...", true);
            }
        }

        @Override
        protected Coin doInBackground(String... strings) {
            CoinService cs;
            Coin coin = null;
            cs = new CoinServiceImpl();

            try {
                coin = cs.getCoin(coinName);
            } catch (IOException  | NetworkErrorException | JSONException e) {
                e.printStackTrace();
            }
            return coin;
        }

        protected void onPostExecute(Coin coin) {

            if (coin != null) {
                cryptoCoin = coin;
                coinNameField.setText(coin.getName());
                coinPriceUSDField.setText(String.valueOf(coin.getPriceUsd()));
                coinPriceBTCField.setText(String.valueOf(coin.getPriceBtc()));
            } else {
                // Handle Error
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
