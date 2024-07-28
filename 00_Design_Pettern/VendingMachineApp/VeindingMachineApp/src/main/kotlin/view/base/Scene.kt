package view.base

import controller.base.IController
import controller.base.SceneState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

abstract class Scene<T: SceneState>(val controller: IController<T>) {
    private val sceneScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var job: Job? = null

    abstract val sceneName: String
    abstract val operation: String
    abstract fun contents(state: T)

    fun run(): Job {
        job =
            sceneScope.launch {
                launch {
                    controller.sceneState
                        .onSubscription { controller.loadCurrentState() }
                        .onEach { state ->
                            if (state.isFinish) {
                                finish()
                                return@onEach
                            }

                            sceneName()
                            spacer()
                            errorMessage(state.errorMessage)
                            operation()
                            spacer()
                            contents(state)

                            controller.nextAction(readln())
                        }
                        .collect()
                }
            }

        return job!!
    }

    protected fun spacer() {
        println()
    }

    protected fun header(resource: String) {
        println(HEADER_FORMAT.format(resource))
    }

    protected fun errorMessage(resource: String?) {
        resource?.let {
            println(ERROR_FORMAT)
            println(it)
            spacer()
        }
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
        private const val ERROR_FORMAT = "✖✖✖ エラー ✖✖✖"
        private const val OPERATION = "操作"
    }
}
