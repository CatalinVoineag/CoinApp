package catalin.coinnews.services;

import android.accounts.NetworkErrorException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import catalin.coinnews.managers.CoinManager;
import catalin.coinnews.models.Coin;
import catalin.coinnews.models.CoinList;
import okhttp3.Response;

/**
 * Created by catalin on 02/12/17.
 */

public class CoinListServiceImpl extends Service implements CoinListService {
    @Override
    public CoinManager getCoins() throws IOException, NetworkErrorException, JSONException {
        String url = "https://api.coinmarketcap.com/v1/ticker/?limit=10";
        JSONObject obj = new JSONObject();
        Response response = this.consumeService(url, obj, "GET");
        boolean result = response.isSuccessful();


        ArrayList<Coin> coins = new ArrayList<>();
        CoinManager manager = null;

        if (result) {
            final String jsonData = response.body().string();
            JSONArray jsonArray = new JSONArray(jsonData);

            Coin coin;
            for (int i=0; i<jsonArray.length(); i++) {
                coin = new Coin();
                JSONObject jsonObj = new JSONObject(jsonArray.get(i).toString());
//                coin.setId(Integer.valueOf(jsonObj.getString("id")));
//                coin.setId(i);
                coin.setName(jsonObj.getString("name"));
                coin.setSymbol(jsonObj.getString("symbol"));
                coin.setRank(Integer.parseInt(jsonObj.getString("rank")));
                coin.setPriceUsd(Float.parseFloat(jsonObj.getString("price_usd")));
                coin.setPriceBtc(Float.parseFloat(jsonObj.getString("price_btc")));
                coin.setH24_volume_usd(Float.parseFloat(jsonObj.getString("24h_volume_usd")));
                coin.setMarket_cap_usd(Float.parseFloat(jsonObj.getString("market_cap_usd")));
                coin.setAvailable_supply(Float.parseFloat(jsonObj.getString("available_supply")));
                coin.setTotal_supply(Float.parseFloat(jsonObj.getString("total_supply")));
//                coin.setMax_supply(Float.parseFloat(jsonObj.getString("max_supply")));
                coin.setPercent_change_1h(Float.parseFloat(jsonObj.getString("percent_change_1h")));
                coin.setPercent_change_24h(Float.parseFloat(jsonObj.getString("percent_change_24h")));
                coin.setPercent_change_7d(Float.parseFloat(jsonObj.getString("percent_change_7d")));
                coin.setLast_updated(Long.parseLong(jsonObj.getString("last_updated")));
                coins.add(coin);
            }
            manager = new CoinManager();
            CoinList coinList = new CoinList();
            coinList.setCoins(coins);
            manager.setCoinList(coinList);
        }
        return manager;
    }
}

