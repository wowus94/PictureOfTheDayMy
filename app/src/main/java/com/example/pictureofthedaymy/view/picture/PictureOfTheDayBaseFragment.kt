package com.example.pictureofthedaymy.view.picture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pictureofthedaymy.R
import com.example.pictureofthedaymy.databinding.FragmentBasePictureOfTheDayBinding
import com.example.pictureofthedaymy.view.navigation.ViewPager2AdapterForPictureOfTheDayFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PictureOfTheDayBaseFragment : Fragment() {

    private var _binding: FragmentBasePictureOfTheDayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasePictureOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ViewPagerPictureOfTheDay.adapter = ViewPager2AdapterForPictureOfTheDayFragment(this)
        bindTabLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun bindTabLayout() {
        TabLayoutMediator(binding.tabLayoutPictureOfTheDay, binding.ViewPagerPictureOfTheDay,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
                    tab.text = when (position) {
                        0 -> {
                            resources.getString(R.string.today)
                        }
                        1 -> {
                            resources.getString(R.string.yesterday)
                        }
                        else -> ""
                    }
                }
            }).attach()
    }

    companion object {
        fun newInstance() = PictureOfTheDayBaseFragment()
    }
}