package view.home

import controller.base.SceneState
import model.product.Product

data class HomeSceneState(
    override val isFinish: Boolean = false,
    override val errorMessage: String? = null,
    val productInfo: Map<Product, Boolean>
) : SceneState