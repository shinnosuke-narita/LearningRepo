package view.menu

import view.menu.public_interface.IMenuRouter
import view.menu.public_interface.IMenuScene

class MenuController(
    private val router: IMenuRouter,
    private val scene: IMenuScene
) {
    companion object {
        private const val SHOW_WALLET = "a"
        private const val SHOW_PRODUCT = "b"
        private const val BUY_DRINK = "c"
        private const val WORK = "d"
    }

    fun nextScene(input: String) {
        when(input) {
            SHOW_WALLET -> { router.pushWalletScene() }
            BUY_DRINK -> { router.pushBuyScene() }
            else -> { router.pushFinishScene() }
        }
    }
}