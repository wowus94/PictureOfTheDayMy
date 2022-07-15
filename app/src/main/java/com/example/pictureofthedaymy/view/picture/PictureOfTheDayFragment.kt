package com.example.pictureofthedaymy.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.pictureofthedaymy.R
import com.example.pictureofthedaymy.databinding.FragmentPictureBinding
import com.example.pictureofthedaymy.viewmodel.PictureOfTheDayAppState
import com.example.pictureofthedaymy.viewmodel.PictureOfTheDayViewModel
import java.text.SimpleDateFormat
import java.util.*

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentPictureBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }


        arguments?.getInt(BUNDLE_PICTURE_OF_THE_DAY).let { date ->
            viewModel.getLiveData().observe(viewLifecycleOwner) {
                renderData(it, date)
            }
            viewModel.sendServerRequest(currentDate.takeDate(date ?: 0))
        }


    }


    private fun renderData(pictureOfTheDayAppState: PictureOfTheDayAppState, date: Int?) {
        when (pictureOfTheDayAppState) {
            is PictureOfTheDayAppState.Error -> {
                binding.imageView.load(R.drawable.ic_load_error_vector) {
                    error(R.drawable.ic_load_error_vector)
                }
            }
            is PictureOfTheDayAppState.Loading -> {
                binding.imageView.load(R.drawable.progress_animation) {
                    placeholder(R.drawable.progress_image)
                }
            }
            is PictureOfTheDayAppState.Success -> {
                binding.imageView.load(pictureOfTheDayAppState.pictureOfTheDayResponseData.url) {
                }
            }
        }
    }

    companion object {
        fun newInstance(bundle: Bundle): PictureOfTheDayFragment {
            val fragment = PictureOfTheDayFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val BUNDLE_PICTURE_OF_THE_DAY = "BUNDLE_PICTURE_OF_THE_DAY"
        private val currentDate = Date()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun Date.takeDate(i: Int): String {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date()).split("-")
        val day = date[2].toInt() - i
        return "${date[0]}-${date[1]}-$day"
    }
}