package view.buy.public_interface

interface IBuyScene {
    fun onError(value: String)
    fun onReceiveDeposit(total: Int, deposit: Int)
}