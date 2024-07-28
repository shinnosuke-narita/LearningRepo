package controller.base

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface IController<T> {
    val sceneState: SharedFlow<T>
    suspend fun nextAction(input: String)
    suspend fun loadCurrentState() {}
}