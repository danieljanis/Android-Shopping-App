package edu.danieljanis.shoppingmvvm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ListDao {

    /* These Queries are for the Titles */

    @Insert
    suspend fun insertTitle(title: TitleEntity)

    @Query("SELECT * FROM TitleTable WHERE id = :id")
    fun getTitle(id: Int): LiveData<TitleEntity>

    @Query("SELECT * FROM TitleTable")
    fun getEveryTitle(): LiveData<List<TitleEntity>>

    @Query("UPDATE TitleTable SET title = :title WHERE id = :id")
    suspend fun editTitle(title: String, id: Int)

    @Query("DELETE FROM TitleTable WHERE id = :id")
    suspend fun removeTitle(id: Int)

    /* These Queries are for the Items */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ItemEntity)

    @Query("SELECT * FROM ItemTable WHERE listId = :listId")
    fun getItem(listId: Int): List<ItemEntity>

    @Query("SELECT * FROM ItemTable WHERE listId = :listId")
    fun getEveryItem(listId: Int): LiveData<List<ItemEntity>>

    @Query("UPDATE ItemTable SET name = :name, cost = :cost, amount = :amount WHERE itemId = :itemId AND listId = :listId")
    suspend fun editItem(name: String, cost: Double, amount: Int, itemId: Int, listId: Int)

    @Query("DELETE FROM ItemTable WHERE itemId = :itemId")
    suspend fun removeItem(itemId: Int)

    @Query("UPDATE ItemTable SET checked = :checked WHERE itemId = :itemId")
    suspend fun updateCheckbox(checked: Boolean, itemId: Int)


}