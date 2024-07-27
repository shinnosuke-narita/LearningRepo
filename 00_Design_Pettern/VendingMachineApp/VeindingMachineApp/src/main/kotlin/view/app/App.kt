package view.app

import model.navigator.Navigator
import view.menu.MenuRouter

class App {
    init {
        Navigator.enqueue(MenuRouter.setUpMenuScene())
    }

    suspend fun run() {
        while(Navigator.existNextScene()) {
            val scene = Navigator.dequeue()
            scene.run().join()
        }
    }
}