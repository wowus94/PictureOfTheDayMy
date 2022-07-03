package com.example.pictureofthedaymy.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pictureofthedaymy.*
import com.example.pictureofthedaymy.databinding.FragmentSettingsBinding
import com.example.pictureofthedaymy.viewmodel.PictureOfTheDayViewModel

class SettingsFragment : Fragment(), View.OnClickListener {

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

        binding.tabDefaultTheme.setOnClickListener(this)
        binding.tabGreenTheme.setOnClickListener(this)
        binding.tabRedTheme.setOnClickListener(this)

        when (parentActivity.getCurrentTheme()) {
            1 -> binding.tabLayout.check(R.id.tabDefaultTheme)
            2 -> binding.tabLayout.check(R.id.tabGreenTheme)
            3 -> binding.tabLayout.check(R.id.tabRedTheme)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tabDefaultTheme -> {
                parentActivity.setCurrentTheme(ThemeDefault)
                parentActivity.recreate()
            }
            R.id.tabGreenTheme-> {
                parentActivity.setCurrentTheme(ThemeGreen)
                parentActivity.recreate()
            }
            R.id.tabRedTheme -> {
                parentActivity.setCurrentTheme(ThemeRed)
                parentActivity.recreate()
            }
        }
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}