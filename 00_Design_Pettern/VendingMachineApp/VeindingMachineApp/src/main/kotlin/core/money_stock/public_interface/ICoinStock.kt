package core.money_stock.public_interface

import core.result.CoreResult
import core.vending_machine.public_interface.IVendingMachine


interface ICoinStock {
    fun hasEnoughChange(vendingMachine: IVendingMachine): CoreResult<Boolean>
}
