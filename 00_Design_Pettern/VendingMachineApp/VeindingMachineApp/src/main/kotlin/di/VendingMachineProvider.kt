package di

import model.deposit.Deposit
import model.money_stock.CoinStock
import model.income.Income
import model.product_stock.ProductStock
import model.vending_machine.VendingMachine
import model.vending_machine.public_interface.IVendingMachine

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