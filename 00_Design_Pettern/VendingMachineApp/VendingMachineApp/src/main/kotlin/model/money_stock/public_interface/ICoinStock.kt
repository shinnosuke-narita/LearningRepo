package model.money_stock.public_interface

import model.result.CoreResult
import model.vending_machine.public_interface.IVendingMachine


interface ICoinStock {
    fun hasEnoughChange(vendingMachine: IVendingMachine): CoreResult<Boolean>
}
