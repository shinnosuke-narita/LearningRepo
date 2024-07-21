package di

import entity.deposit.Deposit
import entity.money_stock.CoinStock
import entity.income.Income
import entity.product_stock.ProductStock
import entity.vending_machine.VendingMachine
import entity.vending_machine.public_interface.IVendingMachine

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