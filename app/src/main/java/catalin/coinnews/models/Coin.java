package catalin.coinnews.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by catalin on 02/12/17.
 */

public class Coin implements Parcelable {

    private String id;
    private String name;
    private String symbol;
    private int rank;
    private float price_usd;
    private float price_btc;
    private Holding[] holdings;
    private boolean alert;

    public Holding[] getHoldings() {
        return holdings;
    }

    public void setHoldings(Holding[] holdings) {
        this.holdings = holdings;
    }

    public boolean getAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public float getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(float price_usd) {
        this.price_usd = price_usd;
    }

    public float getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(float price_btc) {
        this.price_btc = price_btc;
    }

    public Coin() {}

    @Override
    public int describeContents() {
        return 0; // Don't need
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(symbol);
        parcel.writeInt(rank);
        parcel.writeFloat(price_usd);
        parcel.writeFloat(price_btc);
    }

    protected Coin(Parcel in) {
        id = in.readString();
        name = in.readString();
        symbol = in.readString();
        rank = in.readInt();
        price_usd = in.readFloat();
        price_btc = in.readFloat();
    }

    public static final Creator<Coin> CREATOR = new Creator<Coin>() {
        @Override
        public Coin createFromParcel(Parcel parcel) {
            return new Coin(parcel);
        }

        @Override
        public Coin[] newArray(int i) {
            return new Coin[i];
        }
    };
}
