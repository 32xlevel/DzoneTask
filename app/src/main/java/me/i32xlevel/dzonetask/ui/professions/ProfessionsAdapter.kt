package me.i32xlevel.dzonetask.ui.professions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_profession.view.*
import me.i32xlevel.dzonetask.R
import me.i32xlevel.dzonetask.model.Profession

class ProfessionsAdapter(
    private val clickListener: (Profession) -> Unit
) : ListAdapter<Profession, ProfessionsViewHolder>(ProfessionsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfessionsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_profession, parent, false)
        return ProfessionsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfessionsViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class ProfessionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(profession: Profession, clickListener: (Profession) -> Unit) {
        itemView.profession_text.text = profession.name
        itemView.setOnClickListener { clickListener(profession) }
    }
}

class ProfessionsDiffCallback : DiffUtil.ItemCallback<Profession>() {
    override fun areItemsTheSame(oldItem: Profession, newItem: Profession): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Profession, newItem: Profession): Boolean {
        return oldItem == newItem
    }
}