package di

import model.customer.public_interface.ICustomer
import model.customer.public_interface.IWallet
import model.deposit.IDeposit
import model.money_store.public_interface.IMoneyStore
import model.product_stock.public_interface.IProductStock
import model.services.change.IChangeService
import model.services.deposit.IDepositService

object ServiceLocator {
    private val wallet: IWallet get() = Provider.WalletProvider.instance
    val customer: ICustomer get() = Provider.CustomerProvider(wallet).instance
    val deposit: IDeposit get() = Provider.DepositProvider.instance
    val productStock: IProductStock get() = Provider.ProductStockProvider.instance
    private val moneyStore: IMoneyStore get() = Provider.MoneyStockProvider.instance
    private val changeService: IChangeService get() = Provider.ChangeServiceProvider(moneyStore).instance
    val depositService: IDepositService get() =
        Provider.DepositServiceProvider(
            deposit = deposit,
            moneyStore = moneyStore,
            changeService = changeService,
            productStock = productStock
        ).instance
}