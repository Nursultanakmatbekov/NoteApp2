package com.example.noteapp.ui.fragments.onboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardBinding
import com.example.noteapp.ui.adapters.OnBoardViewPagerAdapter
import com.example.noteapp.utils.PreferenceHelper
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setUpListeners()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { it, it2 ->
        }.attach()
        noteFragment()
        clickText()
    }

    private fun initialize() {
        binding.viewPager.adapter = OnBoardViewPagerAdapter(this@OnBoardFragment)
    }

    private fun setUpListeners() = with(binding.viewPager) {
        binding.text1.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 1, true)
            }
            binding.text3.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
            }
        }
    }

    private fun clickText() = with(binding) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        text1.isVisible = true
                        text3.text = ""
                        text3.isVisible = false
                    }
                    1 -> {
                        text1.isVisible = true
                        text3.text = ""
                        text3.isVisible = false
                    }
                    2 -> {
                        text1.isVisible = false
                        text3.text = "Начать работу"
                        text3.isVisible = true
                    }
                }
                super.onPageSelected(position)
            }
        })
    }

    private fun noteFragment() {
        if (PreferenceHelper.isShow) {
            findNavController().navigateUp()
        } else {
            PreferenceHelper.isShow = true
        }
    }
}
