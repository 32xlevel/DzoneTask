package me.i32xlevel.dzonetask.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProfessionTable::class, WorkerTable::class, WorkersProfessionsCrossRef::class], version = 1, exportSchema = false)
abstract class DzoneDatabase : RoomDatabase() {

    abstract val workerDao: WorkerDao
    abstract val professionDao: ProfessionDao

    companion object {
        @Volatile
        private var INSTANCE: DzoneDatabase? = null

        fun getInstance(context: Context): DzoneDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room
                        .databaseBuilder(context.applicationContext, DzoneDatabase::class.java, "dzone.db")
                        .build()
                }

                return instance
            }
        }
    }

}