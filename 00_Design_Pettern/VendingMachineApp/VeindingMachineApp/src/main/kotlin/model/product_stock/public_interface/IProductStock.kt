package model.product_stock.public_interface

import model.product.Product

interface IProductStock {
    fun getLowestPrice(): Int
    fun getAllProductInfo(): Map<Product, Boolean>
}