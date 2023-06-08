package com.example.notedipz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.notedipz.data.Note
import com.example.notedipz.data.noteViewModel
import com.example.notedipz.databinding.FragmentAddNoteBinding

class addNote : Fragment() {
    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() =_binding!!
    private lateinit var mNoteViewModel: noteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddNoteBinding.inflate(inflater,container,false)
        val view = binding.root

    mNoteViewModel = ViewModelProvider(this).get(noteViewModel::class.java)
        binding.btnSave.setOnClickListener{
            insertNoteToDatabase()

        }
        return view
    }

    private fun insertNoteToDatabase(){
        val title = binding.etTitle.text.toString()
        val content = binding.etContent.text.toString()

        val note = Note(0,title,content)
        mNoteViewModel.addNote(note)
        binding.btnSave.setOnClickListener{
            Toast.makeText(context,"Berhasil Menambahkan Catatan",Toast.LENGTH_SHORT).show()
            this.findNavController().navigate(R.id.action_addNote_to_listNote)
        }
    }

}