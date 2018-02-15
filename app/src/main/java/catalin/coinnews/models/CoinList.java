package catalin.coinnews.models;

import java.io.Serializable;
import java.util.ArrayList;

import catalin.coinnews.managers.CoinManager;

/**
 * Created by catalin on 10/02/18.
 */

public class CoinList extends CoinManager implements Serializable {

    private ArrayList<Coin> coins;
    private ArrayList<FavoriteCoin> favCoins;

    public ArrayList<FavoriteCoin> getFavCoins() {
        return favCoins;
    }

    public void setFavCoins(ArrayList<FavoriteCoin> favCoins) {
        this.favCoins = favCoins;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }
}
