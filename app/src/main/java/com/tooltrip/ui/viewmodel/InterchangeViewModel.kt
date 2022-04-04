package com.tooltrip.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tooltrip.data.entities.Interchange
import com.tooltrip.domain.repository.InterchangeRepository
import com.tooltrip.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InterchangeViewModel @Inject constructor(
    private val interchangeRepository: InterchangeRepository
) : ViewModel() {

    private val _interchangeMutableSharedFlow = MutableSharedFlow<MutableList<Interchange>>()
    val interchangeList: MutableSharedFlow<MutableList<Interchange>> = _interchangeMutableSharedFlow
    var error = MutableSharedFlow<String>()

    fun getInterchangeList() {
        viewModelScope.launch {
            val response = interchangeRepository.getInterchangeList()
            when (response.status) {
                Resource.Status.SUCCESS -> {
                    response.data?.let {
                        interchangeList.emit(it)
                    }
                }
                Resource.Status.ERROR -> {
                    error.emit("Something went wrong!")
                }
            }
        }
    }
}
