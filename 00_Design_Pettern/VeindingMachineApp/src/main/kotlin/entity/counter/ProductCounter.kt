package entity.counter

import entity.product.Product

class ProductCounter(
    product: Product,
    amount: Int
) : Counter<Product>(product, amount) {
    val product: Product = data

    override fun isSame(data: Product): Boolean {
        return product.id == data.id
    }
}