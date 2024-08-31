package di

import model.customer.Customer
import model.customer.public_interface.ICustomer
import model.customer.public_interface.IWallet
import model.deposit.Deposit
import model.deposit.DepositAmount
import model.deposit.IDeposit
import model.money_store.MoneyStore
import model.money_store.public_interface.IMoneyStore
import model.product_stock.ProductStock
import model.product_stock.public_interface.IProductStock
import model.services.change.ChangeService
import model.services.change.IChangeService
import model.services.deposit.DepositService
import model.services.deposit.IDepositService
import model.wallet.Wallet

sealed class Provider<T> {
    private var _instance: T? = null
    val instance get() = _instance ?: initialize()

    abstract fun create(): T

    private fun initialize(): T {
        _instance = create()
        return _instance!!
    }
    data object WalletProvider : Provider<IWallet>() {
        override fun create(): IWallet = Wallet(Wallet.getInitialWallet())
    }

    data class CustomerProvider(private val wallet: IWallet) : Provider<ICustomer>() {
        override fun create(): ICustomer = Customer(wallet)
    }

    data object DepositProvider : Provider<IDeposit>() {
        override fun create(): IDeposit = Deposit(DepositAmount(0))
    }

    data object ProductStockProvider : Provider<IProductStock>() {
        override fun create(): IProductStock = ProductStock(ProductStock.getInitialData())
    }

    data object MoneyStockProvider : Provider<IMoneyStore>() {
        override fun create(): IMoneyStore = MoneyStore(MoneyStore.getInitialStock())
    }

    class ChangeServiceProvider(private val moneyStock: IMoneyStore) : Provider<IChangeService>() {
        override fun create(): ChangeService = ChangeService(moneyStock)
    }

    class DepositServiceProvider(
        private val deposit: IDeposit,
        private val moneyStore: IMoneyStore,
        private val changeService: IChangeService,
        private val productStock: IProductStock,
    ) : Provider<IDepositService>() {
        override fun create(): IDepositService =
            DepositService(
                deposit,
                moneyStore,
                changeService,
                productStock
            )
    }
}