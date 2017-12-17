package catalin.coinnews.services;

import android.accounts.NetworkErrorException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import catalin.coinnews.models.Coin;
import okhttp3.Response;

/**
 * Created by catalin on 02/12/17.
 */

public class CoinListServiceImpl extends Service implements CoinListService {
    @Override
    public ArrayList getCoins() throws IOException, NetworkErrorException, JSONException {
        String url = "https://api.coinmarketcap.com/v1/ticker/?limit=10";
        JSONObject obj = new JSONObject();
        Response response = this.consumeService(url, obj, "GET");
        boolean result = response.isSuccessful();


        ArrayList<Coin> coins = new ArrayList<>();

        if (result) {
            final String jsonData = response.body().string();
            JSONArray jsonArray = new JSONArray(jsonData);

            Coin coin;
            for (int i=0; i<jsonArray.length(); i++) {
                coin = new Coin();
                JSONObject jsonObj = new JSONObject(jsonArray.get(i).toString());
                coin.setId(jsonObj.getString("id"));
                coin.setName(jsonObj.getString("name"));
                coin.setSymbol(jsonObj.getString("symbol"));
                coin.setRank(Integer.parseInt(jsonObj.getString("rank")));
                coin.setPrice_usd(Float.parseFloat(jsonObj.getString("price_usd")));
                coin.setPrice_btc(Float.parseFloat(jsonObj.getString("price_btc")));

                coins.add(coin);
            }
        }
        return coins;
    }
}

