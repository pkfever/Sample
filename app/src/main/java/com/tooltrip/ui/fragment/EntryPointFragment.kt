package com.tooltrip.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.tooltrip.R
import com.tooltrip.data.entities.Interchange
import com.tooltrip.databinding.FragmentEntryExitPointBinding
import com.tooltrip.ui.utils.Constants
import com.tooltrip.ui.utils.NavigationGraph
import com.tooltrip.ui.utils.collectLatest
import com.tooltrip.ui.viewmodel.InterchangeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EntryPointFragment : Fragment() {

    private lateinit var binding: FragmentEntryExitPointBinding
    private var rootView: View? = null

    private var interchange: Interchange? = null

    private val interchangeViewModel: InterchangeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntryExitPointBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        setInterchangeData()
//        interchangeViewModel.getInterchangeList()

        NavigationGraph.getFragmentResult<Interchange>(
            requireActivity(),
            NavigationGraph.findNavController(this),
            Constants.INTERCHANGE
        ) {
            binding.interchangeText.text = it.name
            interchange = it
        }

        binding.interchangeText.setOnClickListener {
            openInterchangeDialog()
        }
    }

    private fun setInterchangeData() {

        /* val interchangeList: MutableList<String> = mutableListOf(
             "Zero point",
             "NS Interchange",
             "Ph4 Interchange",
             "Ferozpur Interchange",
             "Lake City Interchange",
             "Raiwand Interchange",
             "Bahria Interchange"
         )
         val interchangeAdapter =
             ArrayAdapter(requireContext(), R.layout.list_item_interchange, interchangeList)
         binding.interchangeAutoCompleteTxt.setAdapter(interchangeAdapter)
         binding.interchangeAutoCompleteTxt.setText(
             binding.interchangeAutoCompleteTxt.adapter.getItem(
                 0
             ).toString(), false
         )*/
    }

    private fun initObserver() {
        lifecycleScope.collectLatest(interchangeViewModel.interchangeList, ::interchangeData)
    }

    private fun interchangeData(interchangeList: MutableList<Interchange>) {

    }

    private fun openInterchangeDialog() {
        NavigationGraph.pushFragment(
            NavigationGraph.findNavController(this),
            R.id.action_entryPointsFragment_to_interchangeDialog,
            null
        )
    }
}