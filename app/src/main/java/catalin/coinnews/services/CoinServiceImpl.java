package catalin.coinnews.services;

import android.accounts.NetworkErrorException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import catalin.coinnews.models.Coin;
import okhttp3.Response;

/**
 * Created by catalin on 17/12/17.
 */

public class CoinServiceImpl extends Service implements CoinService {

    @Override
    public Coin getCoin(String symbol) throws IOException, NetworkErrorException, JSONException {
        String url = String.format("https://api.coinmarketcap.com/v1/ticker/%s/", symbol);
        JSONObject obj = new JSONObject();
        Response response = this.consumeService(url, obj, "GET");
        boolean result = response.isSuccessful();
        Coin coin = new Coin();

        if (result) {
            final String jsonData = response.body().string();
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = new JSONObject(jsonArray.get(0).toString());
            // FIX PRICE FLOAT ISSUE!
//            coin.setId(Integer.valueOf(jsonObject.getString("id")));
            
            coin.setPrice_usd(Float.parseFloat(jsonObject.getString("price_usd")));
            coin.setPrice_btc(Float.parseFloat(jsonObject.getString("price_btc")));
            coin.setName(jsonObject.getString("name"));
            coin.setRank(Integer.parseInt(jsonObject.getString("rank")));
            coin.setSymbol(jsonObject.getString("symbol"));
        }
        return coin;
    }
}
