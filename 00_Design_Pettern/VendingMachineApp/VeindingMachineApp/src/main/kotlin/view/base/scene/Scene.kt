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
    open val operation = ""

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

                    sceneName()
                    spacer()
                    operation()
                    contents(state)

                    controller.nextAction(readln())
                }
            }

        return job!!
    }

    abstract fun showTitle()
    abstract fun showContent()
    open fun contents(state: T) {}

    protected fun spacer() {
        println()
    }

    protected fun header(resource: String) {
        println(HEADER_FORMAT.format(resource))
    }

    protected fun errorMessage(resource: String?) {
        resource?.let { println(ERROR_FORMAT.format(it)) }
    }

    private fun sceneName() {
        println(SCENE_NAME_FORMAT.format(sceneName))
    }

    private fun operation() {
        header(OPERATION)
        println(operation)
    }

    private fun finish() {
        if (job?.isActive == true) {
            job?.cancel()
        }
    }

    companion object {
        private const val SCENE_NAME_FORMAT = "☆☆☆ %s ☆☆☆"
        private const val HEADER_FORMAT = "--- %s ---"
        private const val ERROR_FORMAT = "✖✖✖ %s ✖✖✖"
        private const val OPERATION = "操作"
    }
}
