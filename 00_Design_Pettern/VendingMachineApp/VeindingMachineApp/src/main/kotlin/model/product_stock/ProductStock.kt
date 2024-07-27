package model.product_stock

import model.constants.ProductInfo
import model.counter.ProductCounter
import model.product_stock.public_interface.IProductStock

class ProductStock(
    private val productList: List<ProductCounter>
) : IProductStock {
    companion object {
        fun getInitialData(): List<ProductCounter> {
            return mutableListOf<ProductCounter>().apply {
                ProductInfo.data.forEach { product ->
                    add(ProductCounter(product, 1))
                }
            }
        }
    }

    override fun getLowestPrice(): Int {
        val counter = productList.minBy { counter -> counter.product.price }
        return counter.product.price
    }
}