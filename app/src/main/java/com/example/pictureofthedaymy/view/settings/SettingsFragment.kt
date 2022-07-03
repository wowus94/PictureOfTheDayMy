package com.example.pictureofthedaymy.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pictureofthedaymy.*
import com.example.pictureofthedaymy.databinding.FragmentSettingsBinding
import com.example.pictureofthedaymy.viewmodel.PictureOfTheDayViewModel


class SettingsFragment : Fragment(), View.OnClickListener {

    private lateinit var parentActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity =
            requireActivity() as MainActivity
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnDefaultTheme.setOnClickListener(this)
        binding.btnGreenTheme.setOnClickListener(this)
        binding.btnRedTheme.setOnClickListener(this)

        when (parentActivity.getCurrentTheme()) {
            1 -> binding.radioGroup.check(R.id.btnDefaultTheme)
            2 -> binding.radioGroup.check(R.id.btnGreenTheme)
            3 -> binding.radioGroup.check(R.id.btnRedTheme)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnDefaultTheme -> {
                parentActivity.setCurrentTheme(ThemeDefault)
                parentActivity.recreate()
            }
            R.id.btnGreenTheme -> {
                parentActivity.setCurrentTheme(ThemeGreen)
                parentActivity.recreate()
            }
            R.id.btnRedTheme -> {
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