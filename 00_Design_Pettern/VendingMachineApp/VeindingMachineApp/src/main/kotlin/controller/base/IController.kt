package controller.base

import kotlinx.coroutines.flow.StateFlow

interface IController<T> {
    val sceneState: StateFlow<T>
    suspend fun nextAction(input: String)
}