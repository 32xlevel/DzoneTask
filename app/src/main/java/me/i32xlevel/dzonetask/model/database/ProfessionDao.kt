package me.i32xlevel.dzonetask.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfessionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(profession: ProfessionTable)

    @Query("SELECT * FROM professions")
    suspend fun getAll(): List<ProfessionTable>
}