package me.i32xlevel.dzonetask.viewmodel.professions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.i32xlevel.dzonetask.model.database.ProfessionDao
import me.i32xlevel.dzonetask.model.database.WorkerDao
import me.i32xlevel.dzonetask.model.remote.WorkersAPI

class ProfessionsViewModelFactory(
    private val workersAPI: WorkersAPI,
    private val professionsDao: ProfessionDao,
    private val workerDao: WorkerDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfessionsViewModel::class.java)) {
            return ProfessionsViewModel(workersAPI, professionsDao, workerDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}