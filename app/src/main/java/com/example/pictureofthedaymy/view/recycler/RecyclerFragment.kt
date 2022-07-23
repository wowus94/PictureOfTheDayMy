package com.example.pictureofthedaymy.view.recycler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pictureofthedaymy.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {

    private var _binding: FragmentRecyclerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val data = arrayListOf(
        Data("Заголовок", type = TYPE_HEADER),
        Data("Earth", type = TYPE_EARTH),
        Data("Earth", type = TYPE_EARTH),
        Data("Mars", type = TYPE_MARS),
        Data("Earth", type = TYPE_EARTH),
        Data("Earth", type = TYPE_EARTH),
        Data("Earth", type = TYPE_EARTH),
        Data("Mars", type = TYPE_MARS),
        Data("Earth", type = TYPE_EARTH),
        Data("Earth", type = TYPE_EARTH),
        Data("Mars", type = TYPE_MARS)
    )

    lateinit var adapter: RecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerAdapter(data, callbackAddItem, callbackRemoveItem)
        binding.recyclerView.adapter = adapter
    }

    private val callbackAddItem = AddItem {
        data.add(it, Data("Mars *New*", type = TYPE_MARS))
        adapter.setListDataAdd(data, it)
    }
    private val callbackRemoveItem = RemoveItem {
        adapter.setListDataRemove(data, it)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}