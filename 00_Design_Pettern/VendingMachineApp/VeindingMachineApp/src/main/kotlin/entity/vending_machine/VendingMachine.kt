package entity.vending_machine

import entity.Result
import entity.money.Money
import entity.customer.public_interface.ICustomer
import entity.money_stock.public_interface.ICoinStock
import entity.deposit.public_interface.IDeposit
import entity.income.Income
import entity.product_stock.ProductStock
import entity.vending_machine.public_interface.IVendingMachine

class VendingMachine (
    private val coinStock: ICoinStock,
    private val productStock: ProductStock,
    private val income: Income,
    private val deposit: IDeposit
) : IVendingMachine {
//    fun showCurrentDeposit() {
//        val depositTotal = deposit.getTotal()
//        if (depositTotal > 0) {
//            println(String.format("入金額 %d円", depositTotal))
//        }
//    }

    override fun insertMoney(money: Money, customer: ICustomer): Result<Int?> {
        val result = deposit.insertMoney(money, this)
            .onError { error ->
                customer.returnMoney(money)
                return Result(null, errorMessage = error)
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

    override fun haveEnoughChange(): Result<Boolean> {
        return coinStock.hasEnoughChange(this)
    }
}