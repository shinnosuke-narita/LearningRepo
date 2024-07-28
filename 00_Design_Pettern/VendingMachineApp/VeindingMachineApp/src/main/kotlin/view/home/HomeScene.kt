package view.home

import controller.home.HomeController
import model.product.Product
import view.base.Scene

class HomeScene(controller: HomeController): Scene<HomeSceneState>(controller) {
    override val sceneName: String = "Home画面"
    override val operation: String =
        StringBuilder().apply {
            append("a) BUY DRINK\n")
            append("b) WORK\n")
            append("\n")
            append("c) exit")
        }.toString()

    override fun contents(state: HomeSceneState) {
        products(state.productInfo)
    }

    private fun products(products: Map<Product, Boolean>) {
        header("商品一覧")
        products.forEach { (products, isOnSale) ->
            val saleStatus = if (isOnSale) ON_SALE else SOLD_OUT
            println(PRODUCT_FORMAT.format(products.name, products.price, saleStatus))
        }
    }

    companion object {
        private const val PRODUCT_FORMAT = "・%s(金額: %d円、 ステータス: %s)"
        private const val ON_SALE = "販売中"
        private const val SOLD_OUT = "売り切れ"
    }
}