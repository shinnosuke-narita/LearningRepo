package view.menu

import controller.base.SceneState

data class MenuSceneState(
    override val isFinish: Boolean = false,
    override val errorMessage: String? = null
) : SceneState