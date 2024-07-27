package view.show_wallet

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import view.base.scene.Scene
import view.show_wallet.public_interface.IWalletScene

class WalletScene: Scene(), IWalletScene {
    lateinit var controller: WalletController

    private fun requestWalletData() {
        controller.requestWalletData()
    }

    override fun showTitle() {
        println("-------- 所持金 --------")
    }

    override fun showContent() {}
    override fun initialFunc(): Job =
        sceneScope.launch { controller.requestWalletData() }


    override fun showMoneyIndividual(money: String, amount: Int) {
        println("$money ${amount}枚")
    }

    override fun showTotalMoney(total: Int) {
        println("合計 ${total}円")
        println("----------------------")
    }
}