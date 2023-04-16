package com.example.randomguitargenerator

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomguitargenerator.data.Guitar
import com.example.randomguitargenerator.data.GuitarsApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: GuitarsApi
) : ViewModel() {

    private val _state = mutableStateOf(GuitarState())
    val state: State<GuitarState> = _state

    init {
        getRandomGuitar()
    }

    fun getRandomGuitar() {
        viewModelScope.launch {
            try {
                _state.value = state.value.copy(isLoading = true)
                _state.value = state.value.copy(
                    guitar = api.getRandomGuitar(),
                    isLoading = false
                )
            } catch(e: Exception) {
                Log.e("MainViewModel", "getRandomGuitar: ", e)
                _state.value = state.value.copy(isLoading = false)
            }
        }
    }

    data class GuitarState(
        val guitar: Guitar? = null,
        val isLoading: Boolean = false
    )
}