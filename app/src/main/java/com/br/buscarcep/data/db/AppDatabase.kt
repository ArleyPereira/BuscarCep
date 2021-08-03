package com.br.buscarcep.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.br.buscarcep.data.db.dao.AddressDao
import com.br.buscarcep.data.db.entity.AddressEntity

@Database(entities = [AddressEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val addressDao: AddressDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}