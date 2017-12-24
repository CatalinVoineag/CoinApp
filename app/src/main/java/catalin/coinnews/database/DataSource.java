package catalin.coinnews.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import catalin.coinnews.models.Coin;

/**
 * Created by catalin on 24/12/17.
 */

public class DataSource {

    private Context mContext;
    private SQLiteHelper helper;

    public DataSource(Context context){
        mContext = context;
        helper = new SQLiteHelper(context);
    }

    private SQLiteDatabase open(){
        return helper.getWritableDatabase();
    }

    private void close(SQLiteDatabase database) {
        database.close();
    }

    public void create(Coin coin) {
        SQLiteDatabase database = open();
        database.beginTransaction();
        // Implementation Details

        ContentValues coinValues = new ContentValues();
        coinValues.put(SQLiteHelper.COIN_NAME, coin.getName());
        coinValues.put(SQLiteHelper.COIN_SYMBOL, coin.getSymbol());
        coinValues.put(SQLiteHelper.RANK, coin.getRank());
        coinValues.put(SQLiteHelper.PRICE_USD, coin.getPrice_usd());
        coinValues.put(SQLiteHelper.PRICE_BTC, coin.getPrice_btc());
        coinValues.put(SQLiteHelper.ALERT, coin.getAlert() ? 1 : 0);
        long CoinID = database.insert(SQLiteHelper.COIN_TABLE, null, coinValues);

        database.setTransactionSuccessful();
        database.endTransaction();
        database.close();
    }

}
