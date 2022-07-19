package com.example.pictureofthedaymy.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.example.pictureofthedaymy.databinding.FragmentAnimationBinding

class AnimationFragment : Fragment() {

    private var _binding: FragmentAnimationBinding? = null
    private val binding: FragmentAnimationBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationView()
    }

    var isFlag = false

    private fun animationView() {

        val constraintSetStart = ConstraintSet()
        val constraintSetEnd = ConstraintSet()
        constraintSetStart.clone(binding.constraintContainer)
        constraintSetEnd.clone(binding.constraintContainer)

        binding.tap.setOnClickListener {
            isFlag = !isFlag
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