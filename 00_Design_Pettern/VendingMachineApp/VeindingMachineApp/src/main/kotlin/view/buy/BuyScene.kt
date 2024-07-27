package view.buy

import model.money.Money
import controller.base.IController
import view.base.Scene

class BuyScene(controller: IController<BuySceneState>) : Scene<BuySceneState>(controller) {
    companion object {
        private const val TOTAL_DEPOSIT_FORMAT = "%d円"
        private const val MONEY_FORMAT = "%s: %d枚"
    }

    override val sceneName: String = "商品購入画面"
    override val operation: String =
        StringBuilder().apply {
            append("a{商品番号}) 商品を選択する\n")
            append("b{金額}) お金を入れる\n")
            append("c) お釣り\n")
            append("m) メニューに戻る")
        }.toString()

    override fun contents(state: BuySceneState) {
        errorMessage(state.errorMessage)
        spacer()
        depositInfo(state.totalDeposit)
        spacer()
        walletInfo(state.walletData)
    }

    private fun depositInfo(totalDeposit: Int) {
        header("入金額合計")
        println(TOTAL_DEPOSIT_FORMAT.format(totalDeposit))
    }

    private fun walletInfo(walletInfo: Map<Money, Int>) {
        header("お財布の中身")
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