package catalin.coinnews;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import catalin.coinnews.Interfaces.CoinDao;
import catalin.coinnews.models.Coin;
import catalin.coinnews.models.FavoriteCoin;

/**
 * Created by catalin on 13/02/18.
 */
    @Database(entities = {Coin.class}, version = 1)
        public abstract class AppDatabase extends RoomDatabase {
        public abstract CoinDao coinDao();
    }
