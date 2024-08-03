package view.show_wallet.public_interface

interface IWalletScene {
    fun showMoneyIndividual(money: String, amount: Int)
    fun showTotalMoney(total: Int)
}