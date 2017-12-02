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

public class CoinServiceImpl extends Service implements CoinService {
    @Override
    public ArrayList getCoins() throws IOException, NetworkErrorException, JSONException {
        String url = "https://api.coinmarketcap.com/v1/ticker/?limit=10";
        JSONObject obj = new JSONObject();
        Response response = this.consumeService(url, obj, "GET");
        boolean result = response.isSuccessful();


        ArrayList<Coin> coins = new ArrayList<>();

        if (result) {
            final String jsonData = response.body().string();

//            JSONObject jsonResponse = new JSONObject(jsonData);
//
//            JSONArray jsonTransfers = jsonResponse.getJSONArray("data");
//            JSONObject jsonTransfer;
//            Coin coin;
//            for (int i=0; i<jsonTransfers.length(); i++) {
//                jsonTransfer = jsonTransfers.getJSONObject(i);
//                stock = new Stock();
//                stock.setTitle(title);
//                stock.setStockCount(stockCount);
//
//                stocks.add(stock);
//            }
//        }
        }
        return coins;
    }
}

