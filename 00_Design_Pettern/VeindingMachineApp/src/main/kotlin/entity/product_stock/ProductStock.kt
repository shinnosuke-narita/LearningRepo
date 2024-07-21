package entity.product_stock

import core.constants.ProductInfo
import entity.counter.ProductCounter
import entity.product_stock.public_interface.IProductStock

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