package catalin.coinnews.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by catalin on 24/12/17.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "coinNews.db";
    private static final int DB_VERSION = 16;
    public static final String HOLDING_TABLE = "HOLDINGS";
    public static final String QTY = "QUANTITY";
    public static final String TRADE_PRICE = "TRADING_PRICE";
    public static final String TRADE_DATE = "TRADE_DATE";
    public static final String FOREIGN_KEY = "COIN_ID";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static String CREATE_HOLDINGS = "CREATE TABLE " + HOLDING_TABLE + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            QTY + " INTEGER," +
            TRADE_PRICE + " INTEGER," +
            TRADE_DATE + " INTEGER," +
            "FOREIGN_KEY(" + FOREIGN_KEY + ") REFERENCES COINS(_ID))";


    public static final String COIN_TABLE = "COINS";
    public static final String COIN_NAME = "NAME";
    public static final String COIN_SYMBOL = "SYMBOL";
    public static final String RANK = "RANK";
    public static final String PRICE_USD = "PRICE_USD";
    public static final String PRICE_BTC = "PRICE_BTC";
    public static final String ALERT = "ALERT";

    public static String CREATE_COINS = "CREATE TABLE " + COIN_TABLE + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COIN_NAME+ " STRING," +
            COIN_SYMBOL + " STRING," +
            RANK + " INTEGER," +
            PRICE_USD + " INTEGER," +
            PRICE_BTC + " INTEGER," +
            ALERT + " INTEGER)";



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_COINS);
//        sqLiteDatabase.execSQL(CREATE_HOLDINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
