package catalin.coinnews.Interfaces;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import catalin.coinnews.models.Coin;

/**
 * Created by catalin on 13/02/18.
 */
@Dao
public interface CoinDao {

    @Query("SELECT * FROM coins")
    List<Coin> getAll();

    @Query("SELECT * FROM coins WHERE id IN (:coinIds)")
    List<Coin> findByIds(int[] coinIds);


    @Query("SELECT * FROM coins WHERE id IS :id")
    Coin findById(int id);


    @Query("SELECT * FROM coins WHERE name LIKE :name LIMIT 1")
    Coin findByName(String name);

    @Insert
    void insertAll(Coin... Coins);

    @Delete
    void delete(Coin Coin);

}
