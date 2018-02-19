package catalin.coinnews.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by catalin on 02/12/17.
 */
@Entity(tableName = "coins", indices = {@Index(value = {"name"},
        unique = true)})
public class Coin implements Serializable {
    @PrimaryKey(autoGenerate = true)
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
    @ColumnInfo(name="h24_volume_usd")
    private float h24_volume_usd;
    @ColumnInfo(name="market_cap_usd")
    private float market_cap_usd;
    @ColumnInfo(name="available_supply")
    private float available_supply;
    @ColumnInfo(name="total_supply")
    private float total_supply;
    @ColumnInfo(name="max_supply")
    private float max_supply;
    @ColumnInfo(name="percent_change_1h")
    private float percent_change_1h;
    @ColumnInfo(name="percent_change_24h")
    private float percent_change_24h;
    @ColumnInfo(name="percent_change_7d")
    private float percent_change_7d;
    @ColumnInfo(name="last_updated")
    private long last_updated;

    public Coin( String mName, String mSymbol, int mRank, float priceUsd, float priceBtc) {
//        id = mId;
        name = mName;
        symbol = mSymbol;
        rank = mRank;
        this.priceUsd = priceUsd;
        this.priceBtc = priceBtc;
    }

    public float getH24_volume_usd() {
        return h24_volume_usd;
    }

    public void setH24_volume_usd(float h24_volume_usd) {
        this.h24_volume_usd = h24_volume_usd;
    }

    public float getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(float market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public float getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(float available_supply) {
        this.available_supply = available_supply;
    }

    public float getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(float total_supply) {
        this.total_supply = total_supply;
    }

    public float getMax_supply() {
        return max_supply;
    }

    public void setMax_supply(float max_supply) {
        this.max_supply = max_supply;
    }

    public float getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(float percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public float getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(float percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public float getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(float percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }

    public long getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(long last_updated) {
        this.last_updated = last_updated;
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

    public Coin() {}
//
//    @Override
//    public int describeContents() {
//        return 0; // Don't need
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeInt(id);
//        parcel.writeString(name);
//        parcel.writeString(symbol);
//        parcel.writeInt(rank);
//        parcel.writeFloat(priceUsd);
//        parcel.writeFloat(priceBtc);
//    }

//    protected Coin(Parcel in) {
//        id = in.readInt();
//        name = in.readString();
//        symbol = in.readString();
//        rank = in.readInt();
//        priceUsd = in.readFloat();
//        priceBtc = in.readFloat();
//    }
//
//    public static final Creator<Coin> CREATOR = new Creator<Coin>() {
//        @Override
//        public Coin createFromParcel(Parcel parcel) {
//            return new Coin(parcel);
//        }
//
//        @Override
//        public Coin[] newArray(int i) {
//            return new Coin[i];
//        }
//    };
}
