package me.i32xlevel.dzonetask.extensions

import me.i32xlevel.dzonetask.model.Profession
import me.i32xlevel.dzonetask.model.Worker
import me.i32xlevel.dzonetask.model.database.ProfessionTable
import me.i32xlevel.dzonetask.model.database.WorkerWithProfessions

fun List<ProfessionTable>.toProfession(): List<Profession> {
    return this.map { it.toProfession() }
}

fun ProfessionTable.toProfession(): Profession {
    return Profession(this.professionId, this.name)
}

fun List<WorkerWithProfessions>.toWorker(): List<Worker> {
    return this.map { it.toWorker() }
}

fun WorkerWithProfessions.toWorker(): Worker {
    return Worker(
        id = this.workerTable.workerId,
        firstName = this.workerTable.firstName,
        lastName = this.workerTable.lastName,
        birthday = this.workerTable.birthday,
        avatarUrl = this.workerTable.avatarUrl,
        professions = this.professions.toProfession()
    )
}