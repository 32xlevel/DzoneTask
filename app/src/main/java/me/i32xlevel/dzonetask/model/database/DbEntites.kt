package me.i32xlevel.dzonetask.model.database

import androidx.room.*
import java.util.*

@Entity(tableName = "professions")
data class ProfessionTable(
    @PrimaryKey
    val professionId: Int,
    val name: String
)

@Entity(tableName = "workers")
data class WorkerTable(
    @PrimaryKey
    val workerId: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String,
    val birthday: String? = null,
    val avatarUrl: String? = null
)

/**
 * Many-To-Many relationship
 * See https://medium.com/androiddevelopers/database-relations-with-room-544ab95e4542
 */
@Entity(primaryKeys = ["professionId", "workerId"])
data class WorkersProfessionsCrossRef(
    val professionId: Int,
    val workerId: String
)

data class WorkerWithProfessions(
    @Embedded
    val workerTable: WorkerTable,

    @Relation(
        parentColumn = "workerId",
        entityColumn = "professionId",
        associateBy = Junction(WorkersProfessionsCrossRef::class)
    )
    val professions: List<ProfessionTable>
)