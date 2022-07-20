package com.example.pictureofthedaymy.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.example.pictureofthedaymy.R
import com.example.pictureofthedaymy.databinding.FragmentAnimationStartBinding

class AnimationFragment : Fragment() {

    private var _binding: FragmentAnimationStartBinding? = null
    private val binding: FragmentAnimationStartBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimationStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationView()
    }

    private var isFlag = false

    private fun animationView() {

        val constraintSetStart = ConstraintSet()
        val constraintSetEnd = ConstraintSet()

        constraintSetStart.clone(binding.root.context, R.layout.fragment_animation_start)
        constraintSetEnd.clone(binding.root.context, R.layout.fragment_animation_end)

        binding.tap.setOnClickListener {
            isFlag = !isFlag

            val changeBounds = ChangeBounds()
            changeBounds.interpolator = AnticipateOvershootInterpolator(5.0f)

            TransitionManager.beginDelayedTransition(binding.constraintContainer)
            if (isFlag) {
                constraintSetEnd.applyTo(binding.constraintContainer)
            } else {
                constraintSetStart.applyTo(binding.constraintContainer)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = AnimationFragment()
    }
}