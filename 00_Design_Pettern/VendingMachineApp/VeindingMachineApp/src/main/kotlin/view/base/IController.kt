package view.base

import kotlinx.coroutines.flow.StateFlow

interface IController<T> {
    val sceneState: StateFlow<T>
    fun nextAction(input: String)
}