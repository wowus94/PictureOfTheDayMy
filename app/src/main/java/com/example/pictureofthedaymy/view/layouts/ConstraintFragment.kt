package com.example.pictureofthedaymy.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pictureofthedaymy.databinding.FragmentConstraintBinding

class ConstraintFragment : Fragment() {

    private var _binding: FragmentConstraintBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConstraintBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    var flag = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnThree.setOnClickListener {
           flag = !flag
            binding.group.visibility =
                if (flag) View.VISIBLE else View.GONE
        }
    }

    companion object {
        fun newInstance() = ConstraintFragment()
    }
}