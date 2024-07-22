package entity.money_stock.public_interface

import entity.Result
import entity.vending_machine.public_interface.IVendingMachine

interface ICoinStock {
    fun hasEnoughChange(vendingMachine: IVendingMachine): Result<Boolean>
}
