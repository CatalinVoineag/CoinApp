package catalin.coinnews.services;

import android.accounts.NetworkErrorException;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import catalin.coinnews.managers.CoinManager;
import catalin.coinnews.models.Coin;

/**
 * Created by catalin on 02/12/17.
 */

public interface CoinListService {

    CoinManager getCoins() throws IOException, NetworkErrorException, JSONException;

}
