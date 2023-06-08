package com.example.notedipz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notedipz.data.Note
import com.example.notedipz.data.noteViewModel
import com.example.notedipz.databinding.FragmentAddNoteBinding
import com.example.notedipz.databinding.FragmentListNoteBinding
import java.text.SimpleDateFormat
import java.util.*


class listNote : Fragment() {
    private var _binding: FragmentListNoteBinding? = null
    private val binding get() =_binding!!
    private lateinit var mNoteViewModel: noteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentListNoteBinding.inflate(inflater,container,false)
        val view = binding.root

        val adapter = NoteListAdapter(this)
        val rvNote = binding.rvNote
        rvNote.adapter = adapter

        binding.fabNote.setOnClickListener {
            findNavController().navigate(R.id.action_listNote_to_addNote)
        }
        mNoteViewModel = ViewModelProvider(this).get(noteViewModel::class.java)
        mNoteViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            Note ->adapter.setData(Note)

        })

        rvNote.layoutManager = LinearLayoutManager(requireContext())
        return view
    }
}