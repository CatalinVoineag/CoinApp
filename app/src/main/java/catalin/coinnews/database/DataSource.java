//package catalin.coinnews.database;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.provider.BaseColumns;
//
//import java.util.ArrayList;
//
//import catalin.coinnews.models.Coin;
//
///**
// * Created by catalin on 24/12/17.
// */
//
//public class DataSource {
//
//    private Context mContext;
//    private SQLiteHelper helper;
//
//    public DataSource(Context context){
//        mContext = context;
//        helper = new SQLiteHelper(context);
//    }
//
//    private SQLiteDatabase open(){
//        return helper.getWritableDatabase();
//    }
//
//    private void close(SQLiteDatabase database) {
//        database.close();
//    }
//
//    public void create(Coin coin) {
//        SQLiteDatabase database = open();
//        database.beginTransaction();
//        // Implementation Details
//
//        ContentValues coinValues = new ContentValues();
//        coinValues.put(SQLiteHelper.COIN_NAME, coin.getName());
//        coinValues.put(SQLiteHelper.COIN_SYMBOL, coin.getSymbol());
//        coinValues.put(SQLiteHelper.RANK, coin.getRank());
//        coinValues.put(SQLiteHelper.PRICE_USD, coin.getPriceUsd());
////        coinValues.put(SQLiteHelper.PRICE_BTC, coin.getPriceBtc());
//        long CoinID = database.insert(SQLiteHelper.COIN_TABLE, null, coinValues);
//
//        database.setTransactionSuccessful();
//        database.endTransaction();
//        database.close();
//    }
//
//    public ArrayList<Coin> readCoins () {
//        SQLiteDatabase database = open();
//
//        Cursor cursor = database.query(SQLiteHelper.COIN_TABLE,
//                new String [] {SQLiteHelper.COIN_NAME, BaseColumns._ID, SQLiteHelper.COIN_SYMBOL, SQLiteHelper.RANK, SQLiteHelper.PRICE_USD, SQLiteHelper.PRICE_BTC},
//                null,
//                null,
//                null,
//                null,
//                null);
//        ArrayList<Coin> coins = new ArrayList<Coin>();
//        if (cursor.moveToFirst()) {
//            do {
//                Coin coin = new Coin(getIntFromColumnName(cursor, BaseColumns._ID),
//                        getStringfromColumnName(cursor, SQLiteHelper.COIN_NAME),
//                        getStringfromColumnName(cursor, SQLiteHelper.COIN_SYMBOL),
//                        getIntFromColumnName(cursor, SQLiteHelper.RANK));
//                coins.add(coin);
//            } while(cursor.moveToNext());
//        }
//        cursor.close();
//        close(database);
//        return coins;
//    }
//
//    public void update(Coin coin, int ID) {
//        SQLiteDatabase database = open();
//        database.beginTransaction();
//
//        ContentValues updateCoinValues = new ContentValues();
//        database.update(SQLiteHelper.COIN_TABLE, updateCoinValues, String.format("%s=%d", BaseColumns._ID, ID), null);
//
//        database.setTransactionSuccessful();
//        database.endTransaction();
//        database.close();
//    }
//
//    private int getIntFromColumnName(Cursor cursor, String columnName) {
//        int columnIndex = cursor.getColumnIndex(columnName);
//        return cursor.getInt(columnIndex);
//    }
//
//    private String getStringfromColumnName(Cursor cursor, String columnName) {
//        int columnIndex = cursor.getColumnIndex(columnName);
//        return cursor.getString(columnIndex);
//    }
//
//}
