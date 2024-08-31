package model.services.deposit

import model.customer.public_interface.ICustomer
import model.deposit.DepositAmount
import model.deposit.IDeposit
import model.money.Money
import model.money_store.public_interface.IMoneyStore
import model.product_stock.public_interface.IProductStock
import model.result.CoreResult
import model.services.change.Change
import model.services.change.IChangeService

interface IDepositService {
    fun handleDeposit(money: Money, customer: ICustomer): CoreResult<DepositAmount?>
}

class DepositService(
    private val deposit: IDeposit,
    private val moneyStore: IMoneyStore,
    private val changeService: IChangeService,
    private val productStock: IProductStock,
) : IDepositService {
    override fun handleDeposit(money: Money, customer: ICustomer): CoreResult<DepositAmount?> {
        if (!money.isValid) {
            val error = INVALID_MESSAGE.format(money.name)
            return CoreResult(null, error)
        }

        deposit.add(DepositAmount(money.value))
        moneyStore.add(money)

        val change = deposit.currentDeposit.value - productStock.getLowestPrice()
        if (!changeService.hasEnoughChange(Change(change))) {
            deposit.sub(DepositAmount(money.value))
            moneyStore.sub(money)
            customer.receiveMoney(money)

            return CoreResult(null, NOT_ENOUGH_CHARGE)
        }

        return CoreResult(deposit.currentDeposit)
    }

    companion object {
        private const val INVALID_MESSAGE = "%d%sはご利用いただけません"
        private const val NOT_ENOUGH_CHARGE = "釣銭不足です。\n入金されたお金を返却いたしました。"
    }
}