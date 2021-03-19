package edu.danieljanis.shoppingmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TitleEntity::class, ItemEntity::class], version = 1
)
abstract class ListDatabase: RoomDatabase() {
    companion object {
        @Volatile
        private var instance: ListDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: initDatabase(context).also {
                instance = it
            }
        }

        private fun initDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, ListDatabase::class.java, "ListDb.db")
            .allowMainThreadQueries()
            .build()
    }

    abstract fun getListDao(): ListDao
}