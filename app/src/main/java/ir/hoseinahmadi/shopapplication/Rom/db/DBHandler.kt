package ir.hoseinahmadi.shopapplication.Rom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ir.hoseinahmadi.shopapplication.Rom.db.dao.UserDAO
import ir.hoseinahmadi.shopapplication.Rom.db.model.UserEntity


@Database(
    entities = [UserEntity::class],
    version = DBHandler.DATABASE_VERSION
)
abstract class DBHandler : RoomDatabase() {
    abstract fun userDao(): UserDAO

    companion object {
        private const val DATABASE_NAME = "main_database"
        const val DATABASE_VERSION = 1

        const val USER_TABLE = "userTable"

        private var INSTANCE: DBHandler? = null

        fun getDatabase(context: Context): DBHandler {
            if (INSTANCE == null)
                INSTANCE = Room.databaseBuilder(
                    context,
                    DBHandler::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return INSTANCE!!
        }
    }
}