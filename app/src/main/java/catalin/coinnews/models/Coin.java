package catalin.coinnews.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by catalin on 02/12/17.
 */

public class Coin implements Serializable {

//    private int id;
    private String name;
    private String symbol;
    private int rank;
    private float priceUsd;
    private float priceBtc;

    public Coin( String mName, String mSymbol, int mRank) {
//        id = mId;
        name = mName;
        symbol = mSymbol;
        rank = mRank;
    }

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

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
