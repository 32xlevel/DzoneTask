package me.i32xlevel.dzonetask.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.worker_fragment.*
import me.i32xlevel.dzonetask.R
import me.i32xlevel.dzonetask.extensions.bindAvatar
import me.i32xlevel.dzonetask.extensions.format
import me.i32xlevel.dzonetask.extensions.toAge
import me.i32xlevel.dzonetask.model.Profession
import me.i32xlevel.dzonetask.model.Worker
import me.i32xlevel.dzonetask.viewmodel.UiState
import me.i32xlevel.dzonetask.viewmodel.WorkerViewModel

class WorkerFragment : BaseFragment<WorkerViewModel>(R.layout.worker_fragment) {

    override val viewModel: WorkerViewModel by viewModels()
    private val args: WorkerFragmentArgs by navArgs()

    override fun setupViews() {
        bindData(args.worker, args.professions)
    }

    override fun observeUiState(uiState: UiState) {

    }

    private fun bindData(worker: Worker, professions: Array<Profession>) {
        val name = worker.firstName.toLowerCase().capitalize()
        val lastName = worker.lastName.toLowerCase().capitalize()
        val age = worker.birthday?.toAge() ?: "-"
        val birthday = worker.birthday?.format() ?: "-"

        var profession = ""
        professions.forEach { profession += "${it.name}, " }
        profession = profession.substring(0, profession.lastIndexOf(','))

        (activity as AppCompatActivity).supportActionBar?.title = "$name $lastName"
        worker_name.text = "Имя: $name"
        worker_lastname.text = "Фамилия: $lastName"
        worker_age.text = "Возраст: $age"
        worker_birthday.text = "Дата рождения: $birthday"
        worker_profession.text = "Профессия: $profession"
        worker_avatar.bindAvatar(worker.avatarUrl)

        (activity as AppCompatActivity).supportActionBar?.title = "$name $lastName"
    }
}