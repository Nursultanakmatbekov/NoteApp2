package com.example.noteapp.ui.fragments.onbord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBordPagingBinding

class OnBordPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBordPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBordPagingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        textSetOnClick()
    }

    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                text2.text = "Очень удобный функционал"
                lottieFirst.setAnimation(R.raw.lottie1)
                binding.text3.text = ""
                binding.text3.isVisible = false
            }
            1 -> {
                text2.text = "Быстрый, качественый продукт"
                lottieFirst.setAnimation(R.raw.lottie2)
                imageRectangle.isVisible = false
                binding.text3.text = ""
                binding.text3.isVisible = false
            }
            2 -> {
                text2.text = "Куча функции и интересных фишек"
                lottieFirst.setAnimation(R.raw.lottie3)
                imageRectangle.isVisible = false
                binding.text3.text = "Начать работу"
                binding.text3.isVisible = true
            }
        }
    }

    private fun textSetOnClick() {
        binding.text3.setOnClickListener {
            findNavController().navigate(R.id.action_onBordFragment_to_firstFragment2)
        }
    }

    companion object {
        const val ARG_ONBOARD_POSITION = "onBord"
    }
}