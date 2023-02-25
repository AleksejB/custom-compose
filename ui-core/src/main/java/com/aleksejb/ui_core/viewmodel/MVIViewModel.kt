package com.aleksejb.ui_core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class MVIViewModel<State, Event, Effect>: ViewModel() {

    protected abstract val _state: MutableStateFlow<State>
    val state: StateFlow<State>
        get() = _state

    private val _events: MutableSharedFlow<Event> = MutableSharedFlow()
    private val event = _events.asSharedFlow()

    private val _effects: Channel<Effect> = Channel()
    val effects = _effects.receiveAsFlow()

    init {
        viewModelScope.launch {
            event.collect { event ->
                handleEvent(event)
            }
        }
    }

    protected abstract fun handleEvent(event: Event)

    fun postEvent(event: Event) {
        viewModelScope.launch {
            _events.emit(event)
        }
    }

    protected fun postEffect(effect: Effect) {
        viewModelScope.launch {
            _effects.send(effect)
        }
    }

    protected fun updateState(action: State.() -> State) = _state.update(action)
}