package com.tooltrip.ui.dialog

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tooltrip.R
import com.tooltrip.data.entities.Interchange
import com.tooltrip.databinding.DialogInterchangeBinding
import com.tooltrip.ui.adapter.InterchangeAdapter
import com.tooltrip.ui.utils.Constants
import com.tooltrip.ui.utils.NavigationGraph
import com.tooltrip.ui.utils.collectLatest
import com.tooltrip.ui.viewmodel.InterchangeViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class InterchangeDialog : BottomSheetDialogFragment() {

    private lateinit var binding: DialogInterchangeBinding

    private val interchangeViewModel: InterchangeViewModel by viewModels()
    private var interchangeAdapter: InterchangeAdapter? = null
    private val items: MutableList<Interchange> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            setStyle(STYLE_NORMAL, R.style.theme_bottom_dialog_12)
        } else {
            setStyle(STYLE_NORMAL, R.style.theme_bottom_dialog_10)
        }
        initObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogInterchangeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewData()
        interchangeViewModel.getInterchangeList()
    }

    private fun initObserver() {
        lifecycleScope.collectLatest(interchangeViewModel.interchangeList, ::setData)
    }

    private fun initRecyclerViewData() {

        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rcvData.apply {
            adapter = InterchangeAdapter(requireContext(), items, ::handleClick)
            layoutManager = linearLayoutManager
        }
    }

    fun setData(data: MutableList<Interchange>) {
        items.addAll(data)
        interchangeAdapter?.notifyDataSetChanged()
    }

    private fun handleClick(interchange: Interchange) {
        NavigationGraph.setFragmentResult(
            NavigationGraph.findNavController(this),
            Constants.INTERCHANGE,
            interchange
        )
    }
}