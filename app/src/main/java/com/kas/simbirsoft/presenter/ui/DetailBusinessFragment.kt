package com.kas.simbirsoft.presenter.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kas.simbirsoft.R
import com.kas.simbirsoft.databinding.FragmentDetailBusinessBinding


class DetailBusinessFragment : Fragment(R.layout.fragment_detail_business) {
    private val binding: FragmentDetailBusinessBinding by viewBinding()

    private val args: DetailBusinessFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.business = args.business
    }

}