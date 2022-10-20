package com.aleksejb.ui_core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class MVIViewModel<Effect, Event, State>: ViewModel() {

    init {
        viewModelScope.launch {
            events.collect {
                handleEvent(it)
            }
        }
    }

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    val currentState: State
        get() = _state.value

    private val _effects: Channel<Effect> = Channel()
    val effects = _effects.receiveAsFlow()

    private val _events: MutableSharedFlow<Event> = MutableSharedFlow()
    val events = _events.asSharedFlow()

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    fun postEvent(event: Event) {
        viewModelScope.launch { _events.emit(event) }
    }

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _state.value = newState
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effects.send(effectValue) }
    }

    abstract fun handleEvent(event: Event)
}