package application.vending_machine.product

import model.product.Product
import model.product_stock.public_interface.IProductStock

interface IGetProductInfoApplicationService {
    fun handle(): Map<Product, Boolean>
}

class GetProductInfoApplicationService(
    private val productStock: IProductStock
) : IGetProductInfoApplicationService {
    override fun handle(): Map<Product, Boolean> = productStock.getAllProductInfo()
}