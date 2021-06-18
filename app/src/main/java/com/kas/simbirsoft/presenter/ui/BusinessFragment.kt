package com.kas.simbirsoft.presenter.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kas.simbirsoft.R
import com.kas.simbirsoft.databinding.FragmentBusinessBinding
import com.kas.simbirsoft.presenter.ui.list.BusinessRecyclerView
import com.kas.simbirsoft.presenter.viewModel.BusinessViewModel
import java.util.*


class BusinessFragment : Fragment(R.layout.fragment_business) {
    private val binding: FragmentBusinessBinding by viewBinding()

    private val adapter: BusinessRecyclerView = BusinessRecyclerView()

    private val viewModel: BusinessViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            handler = this@BusinessFragment
            viewModel = this@BusinessFragment.viewModel
        }
        binding.listItem.apply {
            adapter = this@BusinessFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())

        }
        adapter.clickListener = {
            findNavController().navigate(BusinessFragmentDirections.actionBusinessFragmentToDetailBusinessFragment(it))
        }
        binding.calendarView.setOnDateChangeListener { _, year, month, day ->
            onPickDate(year, month, day)
        }
        viewModel.list.observe(viewLifecycleOwner) {
            Log.e("TT1", it.toString())
            adapter.setBusiness(it.first, it.second)
        }
    }
    private fun onPickDate(year: Int, month: Int, day: Int) {
        val date = Calendar.getInstance().apply {
            this[Calendar.YEAR] = year
            this[Calendar.MONTH] = month
            this[Calendar.DAY_OF_MONTH] = day
            this[Calendar.HOUR] = 0
            this[Calendar.MINUTE] = 0
        }
        viewModel.date.postValue(date)
        onClickEditDate()
    }

    fun onClickEditDate() {
        binding.calendarView.apply { isVisible = !isVisible }
    }
}