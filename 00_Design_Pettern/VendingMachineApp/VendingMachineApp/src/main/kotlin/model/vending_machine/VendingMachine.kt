package model.vending_machine

import model.customer.public_interface.ICustomer
import model.deposit.Deposit
import model.deposit.DepositAmount
import model.money.Money
import model.income.Income
import model.money_store.public_interface.IMoneyStore
import model.product.Product
import model.product_stock.public_interface.IProductStock
import model.result.CoreResult
import model.services.change.Change
import model.services.change.ChangeService
import model.vending_machine.public_interface.IVendingMachine

class VendingMachine (
    private val moneyStore: IMoneyStore,
    private val productStock: IProductStock,
    private val deposit: Deposit,
    private val changeService: ChangeService,
    private val income: Income,
) : IVendingMachine {
    override fun onPutMoney(money: Money, customer: ICustomer): CoreResult<Int?> {
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

        return CoreResult(deposit.currentDeposit.value)
    }

    override fun getTotalDeposit(): DepositAmount {
        return deposit.currentDeposit
    }

    override fun getAllProductInfo(): Map<Product, Boolean> {
        return productStock.getAllProductInfo()
    }

    override fun getLowestPrice(): Int {
        return productStock.getLowestPrice()
    }

    companion object {
        private const val INVALID_MESSAGE = "%d%sはご利用いただけません"
        private const val NOT_ENOUGH_CHARGE = "釣銭不足です。\n入金されたお金を返却いたしました。"
    }
}