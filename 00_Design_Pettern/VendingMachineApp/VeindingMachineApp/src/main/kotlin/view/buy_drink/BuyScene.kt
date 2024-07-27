package view.buy_drink

import core.money.Money
import view.base.scene.IController
import view.base.scene.Scene

class BuyScene(controller: IController<BuySceneState>) : Scene<BuySceneState>(controller) {
    companion object {
        private const val ERROR_FORMAT = "✖✖✖ %s ✖✖✖"
        private const val TOTAL_DEPOSIT_FORMAT = "--- 入金額合計 --- \n%d円"
        private const val MONEY_FORMAT = "%s: %d枚"
    }

    override val sceneName: String = "商品購入画面"

    override fun showTitle() {
    }

    override fun showContent() {
    }

    override suspend fun startCollect() {
    }

    override fun showContents(state: BuySceneState) {
        showMenu()
        println()
        showErrorMessage(state.errorMessage)
        println()
        println(TOTAL_DEPOSIT_FORMAT.format(state.totalDeposit))
        println()
        showWalletInfo(state.walletData)
    }

    private fun showErrorMessage(errorMessage: String?) {
        errorMessage?.let { println(String.format(ERROR_FORMAT, it)) }
    }

    private fun showMenu() {
        StringBuilder()
            .apply {
                append("--- 操作 ---\n")
                append("a{商品番号}) 商品を選択する\n")
                append("b{金額}) お金を入れる\n")
                append("c) お釣り\n")
                append("m) メニューに戻る")
            }
            .also {
                print(it.toString())
            }
    }

    private fun showWalletInfo(walletInfo: Map<Money, Int>) {
        println("--- お財布の中身 ---")
        StringBuilder()
            .apply {
                walletInfo.forEach { (money, amount) ->
                    val moneyInfo = String.format(MONEY_FORMAT, money.name, amount)
                    append("$moneyInfo\n")
                }
            }
            .also {
                print(it)
            }
    }
}