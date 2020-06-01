package me.i32xlevel.dzonetask.viewmodel.workers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.i32xlevel.dzonetask.extensions.toWorker
import me.i32xlevel.dzonetask.model.Worker
import me.i32xlevel.dzonetask.model.database.WorkerDao
import me.i32xlevel.dzonetask.viewmodel.BaseViewModel
import me.i32xlevel.dzonetask.viewmodel.UiState

class WorkersViewModel(
    private val workerDao: WorkerDao,
    private val professionId: Int
) : BaseViewModel() {
    private val _data = MutableLiveData<List<Worker>>(emptyList())
    val data: LiveData<List<Worker>>
        get() = _data

    init {
        updateUiState(UiState.LOADING)

        viewModelScope.launch {
            try {
                val workers = workerDao.getWithProfessions(professionId).toWorker()

                if (workers.isNotEmpty()) {
                    _data.postValue(workers)
                    withContext(Dispatchers.Main) {
                        updateUiState(UiState.SUCCESS)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        updateUiState(UiState.EMPTY)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    updateUiState(UiState.ERROR)
                }

                e.printStackTrace()
            }
        }
    }

}