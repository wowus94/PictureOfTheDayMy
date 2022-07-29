package com.example.pictureofthedaymy.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
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
    lateinit var spannableString: SpannableString

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

        helloBtn()
        cropImg()

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

    private fun cropImg() {
        binding.imageView.setOnClickListener {
            isFlag = !isFlag
            val params = it.layoutParams as CoordinatorLayout.LayoutParams

            val transitionSet = TransitionSet()
            val changeBounds = ChangeBounds()
            val changeImageTransform = ChangeImageTransform()

            transitionSet.addTransition(changeImageTransform)
            transitionSet.addTransition(changeBounds)
            TransitionManager.beginDelayedTransition(binding.root, changeImageTransform)

            if (isFlag) {
                params.height = CoordinatorLayout.LayoutParams.MATCH_PARENT
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP

            } else {
                params.height = CoordinatorLayout.LayoutParams.WRAP_CONTENT
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            binding.imageView.layoutParams = params
        }
    }

    var isFlag = false
    private fun helloBtn() {
        binding.btnDescription.setOnClickListener {
            isFlag = !isFlag
            TransitionManager.beginDelayedTransition(binding.containerBtn)
            binding.textView.visibility = if (isFlag) View.VISIBLE else {
                View.GONE
            }
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

                val text = pictureOfTheDayAppState.pictureOfTheDayResponseData.explanation
                spannableString = SpannableString(text)
                binding.textView.text = spannableString
                rainbow(1)
            }
        }
    }

    fun rainbow(i: Int = 1) {
        var currentCount = i
        val x = object : CountDownTimer(20000, 200) {
            override fun onTick(millisUntilFinished: Long) {
                colorText(currentCount)
                currentCount = if (++currentCount > 3) 1 else currentCount
            }

            override fun onFinish() {
                rainbow(currentCount)
            }
        }
        x.start()
    }

    private fun colorText(colorFirstNumber: Int) {
        if (this@PictureOfTheDayFragment.isVisible) {
            binding.textView.setText(spannableString, TextView.BufferType.SPANNABLE)
            spannableString = binding.textView.text as SpannableString
            val map = mapOf(
                0 to ContextCompat.getColor(requireContext(), R.color.red),
                1 to ContextCompat.getColor(requireContext(), R.color.orange),
                2 to ContextCompat.getColor(requireContext(), R.color.white),
                3 to ContextCompat.getColor(requireContext(), R.color.green),
            )
            val spans = spannableString.getSpans(
                0, spannableString.length,
                ForegroundColorSpan::class.java
            )
            for (span in spans) {
                spannableString.removeSpan(span)
            }

            var colorNumber = colorFirstNumber
            for (i in 0 until binding.textView.text.length) {
                if (colorNumber == 3) colorNumber = 0 else colorNumber += 1
                spannableString.setSpan(
                    ForegroundColorSpan(map.getValue(colorNumber)),
                    i, i + 1,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
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