package di

import core.deposit.Deposit
import core.money_stock.CoinStock
import core.income.Income
import core.product_stock.ProductStock
import core.vending_machine.VendingMachine
import core.vending_machine.public_interface.IVendingMachine

object VendingMachineProvider {
    private var instance: VendingMachine? = null

    private fun setUpVendingMachine(): VendingMachine {
        val coinStock = CoinStock(CoinStock.getInitialStock())
        val deposit = Deposit(Deposit.getInitialDeposit())
        val productStock = ProductStock(ProductStock.getInitialData())

        instance = VendingMachine(
            coinStock = coinStock,
            productStock = productStock,
            income = Income(),
            deposit = deposit
        )

        return instance!!
    }

    fun provide(): IVendingMachine {
        return instance ?: setUpVendingMachine()
    }
}