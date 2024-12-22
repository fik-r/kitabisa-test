package com.example.kitabisa_assesment.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kitabisa_assesment.domain.abstraction.repository.AppRepository
import com.example.kitabisa_assesment.presentation.view.university.model.University
import com.example.kitabisa_assesment.presentation.view.university.model.domainToUniversityUIModel
import com.example.kitabisa_assesment.utils.CoroutinesDispatcherProvider
import com.example.kitabisa_assesment.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: AppRepository,
    private val dispatcher: CoroutinesDispatcherProvider
) : ViewModel() {
    private val _data = MutableStateFlow<UIState<List<University>>>(UIState.Loading)
    val data: StateFlow<UIState<List<University>>> = _data

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch(dispatcher.main) {
            _data.value = UIState.Loading
            repository.getList()
                .flowOn(dispatcher.io)
                .catch {
                    _data.value = UIState.Error(it.message.orEmpty())
                }.collect {
                    _data.value = UIState.Success(it.domainToUniversityUIModel())
                }
        }

    }
}