package com.mashup.twotoo.presenter.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.UserType.ME
import com.mashup.twotoo.presenter.home.UserType.PARTNER

@Composable
fun HomeRoute() {
    HomeScreen(
        onBeeButtonClick = {},
    )
}

@Composable
fun HomeScreen(
    onBeeButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        TwoTooImageView(
            modifier = Modifier.fillMaxSize(),
            model = R.drawable.image_background,
            previewPlaceholder = R.drawable.image_background,
        )

        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (
                topBar, homeGoalField, goalAchievement, goalCount,
                homeBackground, beeButton, shotCount, myFlower, partnerFlower,
            ) = createRefs()

            TwoTooMainToolbar(
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                onClickHelpIcon = {},
            )

            HomeGoalField(
                modifier = Modifier.width(327.dp).constrainAs(homeGoalField) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                homeGoalFieldData = HomeGoalFieldData(),
            )
            HomeGoalAchievement(
                modifier = Modifier.width(210.dp).height(59.dp).constrainAs(goalAchievement) {
                    start.linkTo(homeGoalField.start)
                    top.linkTo(homeGoalField.bottom)
                }.background(color = Color.White, shape = RoundedCornerShape(15.dp)),
                goalAchieveDataList = listOf(
                    HomeGoalAchieveData(name = "공주", type = PARTNER, progress = 0.7f),
                    HomeGoalAchieveData(name = "나", type = ME, progress = 0.6f),
                ),
            )
            HomeGoalCount(
                modifier = Modifier.constrainAs(goalCount) {
                    end.linkTo(homeGoalField.end)
                    top.linkTo(goalAchievement.top)
                    bottom.linkTo(goalAchievement.bottom)
                },
                homeGoalCountData = HomeGoalCountData(),
            )
            TwoTooImageView(
                modifier = Modifier.width(380.dp).height(244.dp).constrainAs(homeBackground) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                previewPlaceholder = R.drawable.image_home_background,
                model = R.drawable.image_home_background,
            )
            HomeBeeButton(
                modifier = Modifier.constrainAs(beeButton) {
                    top.linkTo(homeBackground.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }.clickable {
                    onBeeButtonClick()
                },
            )
            HomeShotCountText(
                modifier = Modifier.constrainAs(shotCount) {
                    top.linkTo(beeButton.bottom, margin = 10.19.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                homeShotCountTextData = HomeShotCountTextData(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewgHomeScreen() {
    TwoTooTheme {
        HomeScreen(
            onBeeButtonClick = {},
        )
    }
}
