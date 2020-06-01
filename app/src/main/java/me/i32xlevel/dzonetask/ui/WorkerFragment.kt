package me.i32xlevel.dzonetask.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.worker_fragment.*
import me.i32xlevel.dzonetask.R
import me.i32xlevel.dzonetask.extensions.bindAvatar
import me.i32xlevel.dzonetask.extensions.format
import me.i32xlevel.dzonetask.extensions.toAge
import me.i32xlevel.dzonetask.model.Profession
import me.i32xlevel.dzonetask.model.Worker
import java.util.*

class WorkerFragment : Fragment(R.layout.worker_fragment) {

    private val args: WorkerFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData(args.worker, args.professions)
    }

    private fun bindData(worker: Worker, professions: Array<Profession>) {
        val name = worker.firstName.toLowerCase(Locale.ROOT).capitalize()
        val lastName = worker.lastName.toLowerCase(Locale.ROOT).capitalize()
        val age = worker.birthday?.toAge() ?: "-"
        val birthday = worker.birthday?.format() ?: "-"

        // For some reasons joinToString does not work
        var profession = ""
        professions.forEach { profession += "${it.name}, " }
        profession = profession.substring(0, profession.lastIndexOf(','))

        worker_name.text = getString(R.string.worker_name, name)
        worker_lastname.text = getString(R.string.worker_lastname, lastName)
        worker_age.text = getString(R.string.worker_age, age)
        worker_birthday.text = getString(R.string.worker_birthday, birthday)
        worker_profession.text = getString(R.string.worker_profession, profession)
        worker_avatar.bindAvatar(worker.avatarUrl)

        (activity as AppCompatActivity).supportActionBar?.title = "$name $lastName"
    }
}