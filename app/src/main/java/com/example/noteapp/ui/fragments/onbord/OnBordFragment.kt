package com.example.noteapp.ui.fragments.onbord

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.noteapp.databinding.FragmentOnBordBinding
import com.example.noteapp.ui.adapters.OnBordViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class OnBordFragment : Fragment() {

    private lateinit var binding: FragmentOnBordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setOnClickListeners()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { it, it2 ->
            //Some implementation
        }.attach()
    }

    private fun initialize() {
        binding.viewPager.adapter = OnBordViewPagerAdapter(this@OnBordFragment)
        clickText()
    }

    private fun setOnClickListeners() = with(binding.viewPager) {
        binding.text1.setOnClickListener {
            if (currentItem < 3) {
                setCurrentItem(currentItem + 1, true)
            }
        }
    }

    private fun clickText() = with(binding) {
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        text1.isVisible = true
                    }
                    1 -> {
                        text1.isVisible = true
                    }
                    2 -> {
                        text1.isVisible = false
                    }
                }
                super.onPageSelected(position)
            }
        })
    }
}
