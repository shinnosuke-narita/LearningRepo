package view.base.scene

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

abstract class Scene {
    protected val sceneScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    suspend fun run() {
        showTitle()
        showContent()
        initialFunc().join()
    }

    abstract fun showTitle()
    abstract fun showContent()
    abstract fun initialFunc(): Job
}
