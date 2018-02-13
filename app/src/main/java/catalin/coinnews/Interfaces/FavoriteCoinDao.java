package catalin.coinnews.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import catalin.coinnews.models.Coin;
import catalin.coinnews.models.FavoriteCoin;

/**
 * Created by catalin on 13/02/18.
 */
@Dao
public interface FavoriteCoinDao {

    @Query("SELECT * FROM favorite_coins")
    List<FavoriteCoin> getAll();

//    @Query("SELECT * FROM favorite_coins WHERE id IN (:favoriteCoinIds)")
//    List<FavoriteCoin> loadAllByIds(int[] favoriteCoinIds);
//
//    @Query("SELECT * FROM favorite_coin WHERE first_name LIKE :first AND "
//            + "last_name LIKE :last LIMIT 1")
//    FavoriteCoin findByName(String first, String last);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(FavoriteCoin... favoriteCoins);

    @Delete
    void delete(FavoriteCoin favoriteCoin);

}
