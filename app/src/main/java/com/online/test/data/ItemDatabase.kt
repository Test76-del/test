package com.online.test.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TitleData::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
    abstract fun favouritesListDao(): ListDao
    abstract fun favouritesDetailDao(): FavouritesDetailDao

    companion object {
        @Volatile
        private var instance: ItemDatabase? = null

        fun getDatabase(context: Context) = instance
            ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ItemDatabase::class.java,
                    "favourite_database"
                ).build().also { instance = it }
            }
    }
}