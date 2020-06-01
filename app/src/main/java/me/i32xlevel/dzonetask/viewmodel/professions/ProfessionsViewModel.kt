package me.i32xlevel.dzonetask.viewmodel.professions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.i32xlevel.dzonetask.extensions.toProfession
import me.i32xlevel.dzonetask.model.Profession
import me.i32xlevel.dzonetask.model.database.*
import me.i32xlevel.dzonetask.model.remote.RemoteResponse
import me.i32xlevel.dzonetask.model.remote.WorkersAPI
import me.i32xlevel.dzonetask.viewmodel.BaseViewModel
import me.i32xlevel.dzonetask.viewmodel.UiState

class ProfessionsViewModel(
    private val workersAPI: WorkersAPI,
    private val professionsDao: ProfessionDao,
    private val workerDao: WorkerDao
) : BaseViewModel() {
    private val _data = MutableLiveData<List<Profession>>(emptyList())
    val data: LiveData<List<Profession>>
        get() = _data

    init {
        updateUiState(UiState.LOADING)

        viewModelScope.launch {
            val professions = getProfessionsFromDb()
            if (professions.isEmpty()) {
                try {
                    getRemoteDataAndInsertToDb()
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        updateUiState(UiState.ERROR)
                    }

                    e.printStackTrace()
                }
            } else {
                _data.postValue(professions.toProfession())
                withContext(Dispatchers.Main) {
                    updateUiState(UiState.SUCCESS)
                }
            }
        }
    }

    fun getNewData() {
        updateUiState(UiState.LOADING)

        viewModelScope.launch {
            try {
                getRemoteDataAndInsertToDb()
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    updateUiState(UiState.ERROR)
                }
            }
        }
    }

    private suspend fun getRemoteDataAndInsertToDb() {
        val remoteResponse = getRemoteData()
        insertRemoteDataToDb(remoteResponse)

        val finalProfessions = professionsDao.getAll().toProfession()
        _data.postValue(finalProfessions) // TODO: Излишняя работа!

        withContext(Dispatchers.Main) {
            if (finalProfessions.isEmpty()) updateUiState(UiState.EMPTY)
            else updateUiState(UiState.SUCCESS)
        }
    }

    private suspend fun getProfessionsFromDb(): List<ProfessionTable> {
        return professionsDao.getAll()
    }

    private suspend fun getRemoteData(): RemoteResponse {
        return workersAPI.getAll()
    }

    private suspend fun insertRemoteDataToDb(remoteData: RemoteResponse) {
        remoteData.response.forEach { workerRemote ->
            val workerTable = WorkerTable(
                firstName = workerRemote.firstName,
                lastName = workerRemote.lastName,
                birthday = workerRemote.birthday,
                avatarUrl = workerRemote.avatarUrl
            )

            workerRemote.professions.forEach { professionRemote ->
                professionsDao.insert(ProfessionTable(professionRemote.professionId, professionRemote.name))
                workerDao.insertWorkerAndProfession(WorkersProfessionsCrossRef(professionId = professionRemote.professionId, workerId = workerTable.workerId))
            }
            workerDao.insert(workerTable)
        }
    }


}