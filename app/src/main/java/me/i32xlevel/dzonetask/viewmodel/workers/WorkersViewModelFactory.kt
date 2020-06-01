package me.i32xlevel.dzonetask.viewmodel.workers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.i32xlevel.dzonetask.model.database.WorkerDao

class WorkersViewModelFactory(
    private val workerDao: WorkerDao,
    private val professionId: Int
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkersViewModel::class.java)) {
            return WorkersViewModel(workerDao, professionId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}