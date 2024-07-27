package model.vending_machine

import model.customer.public_interface.ICustomer
import model.money.Money
import model.money_stock.public_interface.ICoinStock
import model.deposit.public_interface.IDeposit
import model.income.Income
import model.product_stock.ProductStock
import model.result.CoreResult
import model.vending_machine.public_interface.IVendingMachine

class VendingMachine (
    private val coinStock: ICoinStock,
    private val productStock: ProductStock,
    private val income: Income,
    private val deposit: IDeposit
) : IVendingMachine {
    override fun insertMoney(money: Money, customer: ICustomer): CoreResult<Int?> {
        val result = deposit.insertMoney(money, this)
            .onError { error ->
                customer.returnMoney(money)
                return CoreResult(null, errorMessage = error)
            }

        return result
    }


    override fun getLowestPrice(): Int {
        return productStock.getLowestPrice()
    }

    override fun getTotalDeposit(): Int {
        return deposit.getTotal()
    }

    override fun getDepositAmount(money: Money): Int {
        return deposit.getAmount(money)
    }

    override fun haveEnoughChange(): CoreResult<Boolean> {
        return coinStock.hasEnoughChange(this)
    }
}