package view.buy_drink.public_interface

interface IBuyController {
    fun putMoney(input: String)
    fun nextAction(input: String)
    fun selectProduct()
    fun getCharge()
    fun onPushMenu()
}