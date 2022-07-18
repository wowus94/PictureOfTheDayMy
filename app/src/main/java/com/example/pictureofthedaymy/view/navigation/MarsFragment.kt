package com.example.pictureofthedaymy.view.navigation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pictureofthedaymy.databinding.FragmentMarsBinding

class MarsFragment : Fragment() {
    private var _binding: FragmentMarsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuMars()
    }

    var isFlag = false

    private fun menuMars() {
        binding.fab.setOnClickListener {
            isFlag = !isFlag
            if (isFlag) {
                ObjectAnimator.ofFloat(binding.plusImageView, View.ROTATION, 0f, 45f).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, -280f)
                    .start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, -190f)
                    .start()
                ObjectAnimator.ofFloat(binding.transparentBackground, View.ALPHA, 0.5f).start()

                binding.optionOneContainer.animate().alpha(1f).setListener(
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.optionOneContainer.isClickable = true
                        }
                    }
                )

                binding.optionTwoContainer.animate().alpha(1f).setListener(
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.optionTwoContainer.isClickable = true
                        }
                    }
                )

            } else {
                ObjectAnimator.ofFloat(binding.plusImageView, View.ROTATION, 45f, 0f).start()
                ObjectAnimator.ofFloat(binding.optionOneContainer, View.TRANSLATION_Y, 0f).start()
                ObjectAnimator.ofFloat(binding.optionTwoContainer, View.TRANSLATION_Y, 0f)
                    .start()
                ObjectAnimator.ofFloat(binding.transparentBackground, View.ALPHA, 0f).start()

                binding.optionOneContainer.animate().alpha(0f).setListener(
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.optionOneContainer.isClickable = false
                        }
                    }
                )

                binding.optionTwoContainer.animate().alpha(0f).setListener(
                    object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            binding.optionTwoContainer.isClickable = false
                        }
                    }
                )

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}