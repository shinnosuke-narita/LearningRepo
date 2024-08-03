package model.product_stock

import model.constants.ProductInfo
import model.product.Product
import model.product_stock.public_interface.IProductStock

class ProductStock(
    private val productList: MutableMap<Product, Int>
) : IProductStock {
    companion object {
        fun getInitialData(): MutableMap<Product, Int> =
            mutableMapOf<Product, Int>().apply {
                ProductInfo.data.forEach { product ->
                    this[product] = 3
                }
            }
    }

    override fun getLowestPrice(): Int =
        productList.keys.minBy { product -> product.price }.price

    override fun getAllProductInfo(): Map<Product, Boolean> =
        mutableMapOf<Product, Boolean>().apply {
            productList.forEach { (product, amount) ->
                this[product] = amount > 0
            }
        }
}