package com.example.noteapp.ui.fragments.noteFragment.detail

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.App
import com.example.noteapp.databinding.FragmentNoteDetailBinding
import com.example.noteapp.model.NoteModel

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLister()
    }

    private fun setUpLister() = with(binding) {
        edText.addTextChangedListener{
            textReady.isVisible = !edText.text.isEmpty()
        }
        imBack.setOnClickListener{
            findNavController().navigateUp()
        }
        textReady.setOnClickListener{
            val et = binding.edText.text.toString()
            App.getInstance()?.noteDao()?.insertNote(NoteModel(et))
            findNavController().navigateUp()
        }
    }
}