package catalin.coinnews;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import catalin.coinnews.Interfaces.FavoriteCoinDao;
import catalin.coinnews.models.FavoriteCoin;

/**
 * Created by catalin on 13/02/18.
 */
    @Database(entities = {FavoriteCoin.class}, version = 1)
        public abstract class AppDatabase extends RoomDatabase {
        public abstract FavoriteCoinDao favoriteCoinDao();
    }
