package com.devjethava.chplapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.devjethava.chplapp.database.dao.UserDao
import com.devjethava.chplapp.database.entity.UserEntity
import com.devjethava.chplapp.helper.Constants

@Database(
    entities = [UserEntity::class],
    version = Constants.DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        Constants.DATABASE_NAME
                    )//.fallbackToDestructiveMigration()
                        .addMigrations(MIGRATION_1_2)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("DROP TABLE IF EXISTS Location")
            }
        }
    }

    abstract fun userDAO(): UserDao
}