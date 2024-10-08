package model.navigator

import view.base.Scene

object Navigator {
    private val sceneQueue: ArrayDeque<Scene<*>> = ArrayDeque()

    private var previousScene: Scene<*>? = null

    fun existNextScene(): Boolean = sceneQueue.isNotEmpty()

    fun dequeue(): Scene<*> {
        val scene = sceneQueue.removeFirst()
        previousScene = scene

        return scene
    }

    fun enqueue(scene: Scene<*>) {
        sceneQueue.add(scene)
    }
}