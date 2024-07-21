package view.base.scene

abstract class Scene {
    fun run() {
        showTitle()
        showContent()
        initialFunc()
    }

    abstract fun showTitle()
    abstract fun showContent()
    abstract fun initialFunc()
}
