package view.buy_drink

import core.money.Money
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import view.base.scene.Scene
import view.buy_drink.public_interface.IBuyController
import view.buy_drink.public_interface.IBuyScene

class BuyScene : Scene(), IBuyScene {
    companion object {
        private const val ERROR_FORMAT = "✖✖✖ %s ✖✖✖"
        private const val DEPOSIT_FORMAT = "%s円を入金しました"
        private const val TOTAL_FORMAT = "入金額合計 %d円"
        private const val MONEY_FORMAT = "%s: %d枚"
    }

    lateinit var controller: IBuyController

    override fun showTitle() {
        println("商品購入")
    }

    override fun showContent() {
        showMenu()
        println()
    }

    override fun startCollect() =
        sceneScope.launch {
            controller.sceneState.collect { state ->
                if (state.isFinish) {
                    cancel()
                    return@collect
                }

                state.errorMessage?.let { println(it)  }
                println()
                println(TOTAL_FORMAT.format(state.totalDeposit))
                println()
                showWalletInfo(state.walletData)

                controller.nextAction(readln())
            }
        }

    override fun onError(value: String) {
        showErrorMessage(value)
        println()
        showMenu()
        println()
        controller.nextAction(readln())
    }

    override fun onReceiveDeposit(total: Int, deposit: Int) {
        println(String.format(DEPOSIT_FORMAT, deposit))
        println(String.format(TOTAL_FORMAT, total))
        println()
        controller.nextAction(readln())
    }

    private fun showErrorMessage(value: String) {
        println(String.format(ERROR_FORMAT, value))
    }

    private fun showMenu() {
        println("-- 操作 --")
        println("a{商品番号}) 商品を選択する")
        println("b{金額}) お金を入れる")
        println("c) お釣り")
        println("m) メニューに戻る")
    }

    private fun showWalletInfo(walletInfo: Map<Money, Int>) {
        println("-- お財布の中身 --")
        val sb = StringBuilder()
        walletInfo.forEach { (money, amount) ->
            val moneyInfo = String.format(MONEY_FORMAT, money.name, amount)
            sb.append(moneyInfo).append("\n")
        }
        print(sb.toString())
    }
}