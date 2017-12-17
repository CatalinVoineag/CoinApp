package catalin.coinnews;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;

import catalin.coinnews.models.Coin;


/**
 * Created by catalin on 17/12/17.
 */

public class HoldingActivity extends Activity {

    private Context context;
    private Coin[] coins;
    private Coin coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holding);
        context = getApplicationContext();

        ArrayList<Parcelable> parcelables = getIntent().getParcelableArrayListExtra(CoinShowActivity.COIN);
        coin = (Coin) parcelables.get(0);

    }
}
