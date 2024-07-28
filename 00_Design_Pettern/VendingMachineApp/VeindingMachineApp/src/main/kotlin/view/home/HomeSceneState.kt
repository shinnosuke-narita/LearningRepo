package view.home

import controller.base.SceneState

data class HomeSceneState(
    override val isFinish: Boolean = false,
    override val errorMessage: String? = null
) : SceneState