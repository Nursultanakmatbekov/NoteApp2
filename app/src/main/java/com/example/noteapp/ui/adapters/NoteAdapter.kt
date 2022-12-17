package com.example.noteapp.ui.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.ItemBinding
import com.example.noteapp.model.NoteModel

class NoteAdapter(
) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var list: List<NoteModel> = ArrayList()

    fun setList (list: List<NoteModel>){
        this.list = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(s: NoteModel) {
            binding.tvItem.text = s.title
            binding.dataItem.text = s.data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}