package catalin.coinnews.services;

import android.accounts.NetworkErrorException;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import catalin.coinnews.models.Coin;

/**
 * Created by catalin on 02/12/17.
 */

public interface CoinListService {

    ArrayList<Coin> getCoins() throws IOException, NetworkErrorException, JSONException;

}
