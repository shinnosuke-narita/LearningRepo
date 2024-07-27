package core.app

import core.navigator.Navigator
import kotlinx.coroutines.*
import view.menu.MenuRouter

class App {
    init {
        Navigator.enqueue(MenuRouter.setUpMenuScene())
    }

    fun run(): Job {
        val scope = CoroutineScope(Job() + Dispatchers.Default)
        return scope.launch {
            while(Navigator.existNextScene()) {
                val scene = Navigator.dequeue()
                scene.run().join()
            }
        }
    }
}