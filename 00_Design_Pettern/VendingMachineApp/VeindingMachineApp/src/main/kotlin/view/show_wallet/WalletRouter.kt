package view.show_wallet

import core.navigator.Navigator
import view.base.scene.Scene
import di.PersonProvider
import view.menu.MenuRouter
import view.show_wallet.public_interface.IWalletRouter

//class WalletRouter : IWalletRouter {
//    companion object {
//        fun setUpScene(): Scene {
//            val router = WalletRouter()
//            val scene = WalletScene()
//            val controller = WalletController(
//                PersonProvider.provide(),
//                scene,
//                router
//            )
//            scene.controller = controller
//
//            return scene
//        }
//    }
//
//    override fun pushMenuScene() {
//        val menuScene = MenuRouter.setUpMenuScene()
//        Navigator.enqueue(menuScene)
//    }
//}