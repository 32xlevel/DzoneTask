package me.i32xlevel.dzonetask.model.database

import androidx.room.*

@Dao
interface WorkerDao {
    @Insert
    suspend fun insert(worker: WorkerTable)

    @Insert
    suspend fun insertWorkerAndProfession(workersProfessionsCrossRef: WorkersProfessionsCrossRef)

    @Transaction
    @Query("SELECT * FROM workers")
    suspend fun getAllWithProfessions(): List<WorkerWithProfessions>
}