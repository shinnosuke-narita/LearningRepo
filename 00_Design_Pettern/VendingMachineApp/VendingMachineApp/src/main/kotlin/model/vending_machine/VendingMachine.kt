package model.vending_machine

import model.customer.public_interface.ICustomer
import model.money.Money
import model.money_stock.public_interface.IMoneyStore
import model.income.Income
import model.product.Product
import model.product_stock.public_interface.IProductStock
import model.result.CoreResult
import model.vending_machine.public_interface.IVendingMachine

class VendingMachine (
    private val moneyStore: IMoneyStore,
    private val productStock: IProductStock,
    private val income: Income,
) : IVendingMachine {
    override fun onPutMoney(money: Money, customer: ICustomer): CoreResult<Int?> =
        moneyStore.putMoney(money, productStock.getLowestPrice()).let { result ->
            if (result.isError()) {
                customer.returnMoney(money)
                CoreResult(null, errorMessage = result.errorMessage)
            } else {
                CoreResult(result.data!!.value)
            }
        }

    override fun getTotalDeposit(): Int {
        return moneyStore.deposit.value
    }

    override fun getAllProductInfo(): Map<Product, Boolean> {
        return productStock.getAllProductInfo()
    }

    override fun getLowestPrice(): Int {
        return productStock.getLowestPrice()
    }
}