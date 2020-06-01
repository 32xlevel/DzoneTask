package me.i32xlevel.dzonetask.model.database

import androidx.room.*

@Dao
interface WorkerDao {
    @Insert
    suspend fun insert(worker: WorkerTable)

    @Transaction
    @Query("SELECT * FROM workers, professions WHERE professionId = :professionId")
    suspend fun getWithProfessions(professionId: Int): List<WorkerWithProfessions>
}