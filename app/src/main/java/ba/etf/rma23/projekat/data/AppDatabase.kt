package ba.etf.rma23.projekat.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ba.etf.rma23.projekat.GameReview


@Database(entities = arrayOf(GameReview::class), version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun GRDAO(): GameReviewDAO

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "rma23",
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}

