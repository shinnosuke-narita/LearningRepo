package di

import model.deposit.Deposit
import model.deposit.DepositAmount
import model.income.Income
import model.money_store.MoneyStore
import model.product_stock.ProductStock
import model.services.change.ChangeService
import model.vending_machine.VendingMachine
import model.vending_machine.public_interface.IVendingMachine

object VendingMachineProvider {
    private var instance: VendingMachine? = null

    private fun setUpVendingMachine(): VendingMachine {
        val coinStock = MoneyStore(MoneyStore.getInitialStock())
        val productStock = ProductStock(ProductStock.getInitialData())
        instance =
            VendingMachine(
                moneyStore = coinStock,
                productStock = productStock,
                deposit = Deposit(DepositAmount(0)),
                changeService = ChangeService(coinStock),
                income = Income()
            )

        return instance!!
    }

    fun provide(): IVendingMachine {
        return instance ?: setUpVendingMachine()
    }
}