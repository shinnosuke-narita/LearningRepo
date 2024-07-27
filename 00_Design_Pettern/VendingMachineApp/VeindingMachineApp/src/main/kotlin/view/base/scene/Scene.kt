package view.base.scene

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.StateFlow

abstract class SceneState(open val isFinish: Boolean)

interface IController<T> {
    val sceneState: StateFlow<T>
    fun nextAction(input: String)
}

abstract class Scene<T: SceneState>(val controller: IController<T>) {
    private val sceneScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var job: Job? = null

    abstract val sceneName: String

    fun run(): Job {
        showTitle()
        showContent()
        job =
            sceneScope.launch {
                controller.sceneState.collect { state ->
                    if (state.isFinish) {
                        finish()
                        return@collect
                    }

                    showSceneName()
                    spacer()
                    showContents(state)

                    controller.nextAction(readln())
                }
            }

        return job!!
    }

    private fun finish() {
        if (job?.isActive == true) {
            job?.cancel()
        }
    }

    abstract fun showTitle()
    abstract fun showContent()
    abstract suspend fun startCollect()
    open fun showContents(state: T) {}

    protected fun spacer() {
        println()
    }

    private fun showSceneName() {
        println(SCENE_NAME_FORMAT.format(sceneName))
    }

    companion object {
        private const val SCENE_NAME_FORMAT = "☆☆☆ %s ☆☆☆"
        const val HEADER_FORMAT = "--- %s ---"
    }
}
