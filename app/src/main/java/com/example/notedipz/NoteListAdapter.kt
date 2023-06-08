package com.example.notedipz

import   android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notedipz.data.Note
import com.example.notedipz.data.noteViewModel
import java.text.SimpleDateFormat
import java.util.*

class NoteListAdapter(private val fragment:Fragment): RecyclerView.Adapter<NoteListAdapter.MyViewHolder>() {

    private var noteList = emptyList<Note>()

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val viewModel = ViewModelProvider(fragment).get(noteViewModel::class.java)

        val currentItem = noteList[position]
        holder.itemView.findViewById<TextView>(R.id.tv_id).text = (position+1).toString()
        holder.itemView.findViewById<TextView>(R.id.tv_title).text = currentItem.title
        holder.itemView.findViewById<TextView>(R.id.tv_content).text = currentItem.content
        holder.itemView.findViewById<TextView>(R.id.tv_tanggal).text = getCurrentDate()

        //untuk menambahkan aksi pada layout custom
        holder.itemView.findViewById<ConstraintLayout>(R.id.customLayout).setOnClickListener {
            val action = listNoteDirections.actionListNoteToUpdateNote(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
        //Untuk fungsi Button Hapus
        holder.itemView.findViewById<ImageButton>(R.id.btnhapus).setOnClickListener {
            val sampah = Note(currentItem.id,currentItem.title,currentItem.content)
            viewModel.deleteNote(sampah)

            // Untuk Fungsi Alert Dialog pada android studio
//            val builder = AlertDialog.Builder(requireContext())
//            builder.setMessage("Yakin Akan Menghapus Data Ini?").setCancelable(false)
//                .setPositiveButton("Ya"){
//                        dialog, id ->  viewModel.deleteNote(sampah)
//                }
//                .setNegativeButton("Tidak"){
//                        dialog, id ->
//                }
//            val alert = builder.create()
//            alert.show()
        }
    }

    fun setData(note: List<Note>){
        this.noteList = note
        Log.d("Gudang-List",note.toString())
        notifyDataSetChanged()
    }

    private fun getCurrentDate():String{
        val dateFormat = SimpleDateFormat("yyyy/MM/dd ", Locale.getDefault())
        val date = Date()

        return dateFormat.format(date)
    }

}