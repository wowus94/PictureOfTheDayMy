package com.example.pictureofthedaymy.view.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pictureofthedaymy.MainActivity
import com.example.pictureofthedaymy.R
import com.example.pictureofthedaymy.databinding.FragmentSettingsBinding
import com.example.pictureofthedaymy.viewmodel.PictureOfTheDayViewModel
import com.google.android.material.tabs.TabLayout


class SettingsFragment : Fragment() {

    private lateinit var parentActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = (context as MainActivity)

    }

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTabLayoutClicks()

    }

    private fun setTabLayoutClicks() = with(binding) {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        SharedPreferences.setCurrentTheme(requireContext(), R.style.ThemeDefault)
                    }
                    1 -> {
                        SharedPreferences.setCurrentTheme(requireContext(), R.style.ThemeGreen)
                    }
                    2 -> {
                        SharedPreferences.setCurrentTheme(requireContext(), R.style.ThemeRed)
                    }
                }
                requireActivity().recreate()
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }


    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
