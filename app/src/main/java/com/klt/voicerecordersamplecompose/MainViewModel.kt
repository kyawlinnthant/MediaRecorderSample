package com.klt.voicerecordersamplecompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klt.voicerecordersamplecompose.screen.Actions
import com.klt.voicerecordersamplecompose.screen.Events
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _events = MutableSharedFlow<Events>()
    val event: SharedFlow<Events> get() = _events
    fun onAction(action: Actions) {
        when (action) {
            is Actions.ShowSnack -> {
                viewModelScope.launch {
                    _events.emit(
                        Events.ShowSnack(status = action.status)
                    )
                }
            }
        }
    }
}