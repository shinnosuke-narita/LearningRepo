package di

import model.income.Income
import model.money_stock.MoneyStore
import model.product_stock.ProductStock
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
                income = Income()
            )

        return instance!!
    }

    fun provide(): IVendingMachine {
        return instance ?: setUpVendingMachine()
    }
}