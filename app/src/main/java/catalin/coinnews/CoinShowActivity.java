package catalin.coinnews;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;

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
    private Coin coin;
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
        coin = (Coin) getIntent().getSerializableExtra("Coin");
        ButterKnife.bind(this);


        if (coin != null) {
            CoinShowActivity.this.coin = coin;
            coinNameField.setText(coin.getName());
            coinPriceUSDField.setText(String.valueOf(coin.getPriceUsd()));
            coinPriceBTCField.setText(String.valueOf(coin.getPriceBtc()));
        } else {
            // Handle Error
        }
    }

}
