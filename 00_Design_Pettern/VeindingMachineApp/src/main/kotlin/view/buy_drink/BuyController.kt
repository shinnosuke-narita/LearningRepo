package view.buy_drink

import view.buy_drink.public_interface.IBuyController
import view.buy_drink.public_interface.IBuyRouter
import view.buy_drink.public_interface.IBuyScene
import domain.buy.BuyUseCase
import domain.buy.model.InputMoney

class BuyController(
    private val router: IBuyRouter,
    private val scene: IBuyScene,
    private val interactor: BuyUseCase
): IBuyController {
    companion object {
        private const val PRODUCT_PREFIX = "a"
        private const val DEPOSIT_PREFIX = "b"
        private const val CHARGE_INPUT = "c"
        private const val MENU_INPUT = "m"
        private const val INVALID_INPUT = "無効な入力値です"
    }

    override fun putMoney(input: String) {
        val result = InputMoney.create(input)
        if (result.isError()) {
            scene.onError(result.errorMessage)
            return
        }

        interactor.putMoney(result.data!!)
            .onSuccess { output ->
                output?.let { scene.onReceiveDeposit(it.total, it.deposit) }
            }
            .onError { error ->
                scene.onError(error)
            }
    }

    override fun nextAction(input: String) {
        when {
            input.startsWith(PRODUCT_PREFIX) -> {

            }
            input.startsWith(DEPOSIT_PREFIX) -> {
                putMoney(input.replace(DEPOSIT_PREFIX, ""))
            }
            input == CHARGE_INPUT -> {

            }
            input == MENU_INPUT -> {
                onPushMenu()
                router.pushMenu()
            }
            else -> {
                scene.onError(INVALID_INPUT)
            }
        }
    }

    override fun selectProduct() {}
    override fun getCharge() {}
    override fun onPushMenu() {}
}