package com.example.noteapp.ui.fragments.noteFragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.model.NoteModel
import java.text.SimpleDateFormat

class NoteDetailFragment : Fragment() {

    private var binding: FragmentNoteDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLister()
    }

    private fun setUpLister() {
        binding?.edText?.addTextChangedListener {
            binding?.textReady?.isVisible = binding?.edText?.text?.isEmpty() == false
        }
        binding?.imBack?.setOnClickListener { _ ->
            findNavController().navigateUp()
        }
        binding?.textReady?.setOnClickListener { _ ->
            val et = binding?.edText?.text.toString()
            val clock = binding?.textClock?.text.toString()
            val data = SimpleDateFormat.getDateInstance()
            App.appDataBase?.noteDao()?.insertNote(NoteModel(et,clock,data.toString()))
            findNavController().navigateUp()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

