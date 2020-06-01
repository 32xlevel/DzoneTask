package me.i32xlevel.dzonetask.ui.workers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_worker.view.*
import me.i32xlevel.dzonetask.R
import me.i32xlevel.dzonetask.extensions.bindAvatar
import me.i32xlevel.dzonetask.extensions.toAge
import me.i32xlevel.dzonetask.model.Worker
import java.util.*

class WorkersAdapter(
    private val clickListener: (Worker) -> Unit
) : ListAdapter<Worker, WorkerViewHolder>(WorkersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_worker, parent, false)
        return WorkerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class WorkerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(worker: Worker, clickListener: (Worker) -> Unit) {
        // name
        itemView.worker_name.text = itemView.context.getString(R.string.worker_name, worker.firstName.toLowerCase(Locale.ROOT).capitalize())
        itemView.worker_lastname.text = itemView.context.getString(R.string.worker_age, worker.lastName.toLowerCase(Locale.ROOT).capitalize())

        // age
        val age: String = worker.birthday?.toAge() ?: "-"
        if (age.isBlank() || age == "-") itemView.worker_age.isVisible = false
        else {
            itemView.worker_age.isVisible = true
            itemView.worker_age.text = itemView.context.getString(R.string.worker_age, age)
        }

        // avatar
        itemView.worker_avatar.bindAvatar(worker.avatarUrl)

        itemView.setOnClickListener { clickListener(worker) }
    }
}

class WorkersDiffCallback : DiffUtil.ItemCallback<Worker>() {
    override fun areItemsTheSame(oldItem: Worker, newItem: Worker): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Worker, newItem: Worker): Boolean {
        return oldItem == newItem
    }
}