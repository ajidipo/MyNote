package com.example.notedipz

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notedipz.data.Note
import com.example.notedipz.data.noteViewModel
import com.example.notedipz.databinding.FragmentListNoteBinding
import com.example.notedipz.databinding.FragmentUpdateNoteBinding

class updateNote : Fragment() {
    private val args by navArgs<updateNoteArgs>()
    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() =_binding!!
    private lateinit var mNoteViewModel: noteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNoteBinding.inflate(inflater,container,false)
        val view = binding.root


        mNoteViewModel = ViewModelProvider(this).get(noteViewModel::class.java)

        binding.etTitle.setText(args.currentNote.title)
        binding.etContent.setText(args.currentNote.content)


        binding.btnUpdate.setOnClickListener{
            updateNote(args.currentNote.id)
        }


        binding.btnDelete.setOnClickListener{
            val id1 = args.currentNote.id
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Yakin Akan Menghapus Data Ini?").setCancelable(false)
                .setPositiveButton("Ya"){
                    dialog, id -> deleteNote(id1)
                }
                .setNegativeButton("Tidak"){
                    dialog, id ->
                }
            val alert = builder.create()
            alert.show()
        }
        return view
    }

    private fun updateNote(id: Int){
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()

        val note = Note(id, title, content)
        mNoteViewModel.updateNote(note)
        findNavController().navigate(R.id.action_updateNote_to_listNote)
    }

    private fun deleteNote(id:Int){
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()

        val note = Note(id, title, content)
        mNoteViewModel.deleteNote(note)
        Toast.makeText(context,"Berhasil Menghapus Catatan",Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateNote_to_listNote)
    }
}