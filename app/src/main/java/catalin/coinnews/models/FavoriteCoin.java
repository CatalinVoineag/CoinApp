package catalin.coinnews.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by catalin on 13/02/18.
 */
@Entity(tableName = "favorite_coins")
public class FavoriteCoin {
    @PrimaryKey
    private int id;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="symbol")
    private String symbol;
    @ColumnInfo(name="rank")
    private int rank;
    @ColumnInfo(name="price_usd")
    private float priceUsd;
    @ColumnInfo(name="price_btc")
    private float priceBtc;

    public FavoriteCoin(String name, String symbol, int rank, float priceUsd, float priceBtc) {
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.priceUsd = priceUsd;
        this.priceBtc = priceBtc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public float getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(float priceUsd) {
        this.priceUsd = priceUsd;
    }

    public float getPriceBtc() {
        return priceBtc;
    }

    public void setPriceBtc(float priceBtc) {
        this.priceBtc = priceBtc;
    }
}
