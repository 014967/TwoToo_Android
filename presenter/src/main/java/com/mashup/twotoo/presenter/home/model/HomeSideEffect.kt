package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/07/05
 */
sealed class HomeSideEffect {
    data class Toast(val text: ToastText) : HomeSideEffect()
    object OpenToShotBottomSheet : HomeSideEffect()
    object OpenToAuthBottomSheet : HomeSideEffect()
    object NavigateToChallengeDetail : HomeSideEffect()
    object OpenToCheerBottomSheet : HomeSideEffect()
    object OpenToHelp : HomeSideEffect()
    object NavigationToCreateChallenge : HomeSideEffect()
    object DismissBottomSheet : HomeSideEffect()
}

enum class ToastText {
    CommitSuccess, CommitFail, ShotSuccess, CheerSuccess, LoadHomeFail
}
