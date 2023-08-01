package com.mashup.twotoo.presenter.history.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.twotoo.presenter.di.daggerViewModel
import com.mashup.twotoo.presenter.history.HistoryRoute
import com.mashup.twotoo.presenter.history.datail.HistoryDetailRoute
import com.mashup.twotoo.presenter.history.di.HistoryComponentProvider
import com.mashup.twotoo.presenter.home.model.HomeGoalAchievePartnerAndMeUiModel
import com.mashup.twotoo.presenter.navigation.NavigationRoute
import com.mashup.twotoo.presenter.util.MoshiUtils
import com.mashup.twotoo.presenter.util.componentProvider

fun NavController.navigateToHistory(challengeNo: Int, homeGoalAchievePartnerAndMeUiModel: HomeGoalAchievePartnerAndMeUiModel? = null) {
    if (homeGoalAchievePartnerAndMeUiModel != null) {
        val progress = MoshiUtils.toJson(homeGoalAchievePartnerAndMeUiModel)
        this.navigate(route = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/$challengeNo?progress=$progress")
    } else {
        this.navigate(route = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/$challengeNo")
    }
}

private fun NavController.navigateToHistoryDetail(commitNo: Int) {
    this.navigate(route = "${NavigationRoute.HistoryGraph.HistoryDetailScreen.route}/$commitNo")
}

@SuppressLint("UnrememberedGetBackStackEntry")
fun NavGraphBuilder.historyGraph(navController: NavController) {
    navigation(startDestination = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/{challengeNo}?progress={progress}", route = NavigationRoute.HistoryGraph.route) {
        composable(
            route = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/{challengeNo}?progress={progress}",
            arguments = listOf(
                navArgument("challengeNo") { type = NavType.IntType },
                navArgument("progress") {
                    nullable = true
                    defaultValue = null
                    type = NavType.StringType
                },
            ),
        ) {
                navBackStackEntry ->
            val challengeNo = navBackStackEntry.arguments?.getInt("challengeNo") ?: 0
            val progress = navBackStackEntry.arguments?.getString("progress")
            val homeGoalAchievePartnerAndMeUiModel = progress?.let {
                MoshiUtils.fromJson<HomeGoalAchievePartnerAndMeUiModel>(progress)
            }

            val historyComponent = componentProvider<HistoryComponentProvider>().provideHistoryComponent()
            val historyViewModel = daggerViewModel {
                historyComponent.getViewModel()
            }
            HistoryRoute(
                challengeNo = challengeNo,
                historyViewModel = historyViewModel,
                homeGoalAchievePartnerAndMeUiModel = homeGoalAchievePartnerAndMeUiModel,
                onClickBackButton = { navController.popBackStack() },
                navigateToHistoryDetail = { commitNo ->
                    navController.navigateToHistoryDetail(commitNo)
                },
            )
        }

        composable(
            route = "${NavigationRoute.HistoryGraph.HistoryDetailScreen.route}/{commitNo}",
            arguments = listOf(
                navArgument("commitNo") { type = NavType.IntType },
            ),
        ) {
                navBackStackEntry ->
            val parentRoute = "${NavigationRoute.HistoryGraph.HistoryScreen.route}/{challengeNo}?progress={progress}"
            val parentEntry = remember {
                navController.getBackStackEntry(parentRoute)
            }
            val viewModelStoreOwner: ViewModelStoreOwner = parentEntry

            val commitNo = navBackStackEntry.arguments?.getInt("commitNo") ?: 0
            val historyComponent = componentProvider<HistoryComponentProvider>().provideHistoryComponent()
            val historyViewModel = daggerViewModel(viewModelStoreOwner = viewModelStoreOwner) {
                historyComponent.getViewModel()
            }
            HistoryDetailRoute(
                commitNo = commitNo,
                historyViewModel = historyViewModel,
                onClickBackButton = { navController.popBackStack() },
            )
        }
    }
}
