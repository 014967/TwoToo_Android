package com.mashup.twotoo.presenter.history.datail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink

@Composable
fun HistoryDetailScreen() {
    Scaffold(
        topBar = { TwoTooMainToolbar(title = "왕자의 기록") },
        containerColor = TwoTooTheme.color.backgroundYellow,
    ) {
        CompositionLocalProvider(
            LocalContentColor provides TwoTooTheme.color.mainBrown,
        ) {
            Column(modifier = Modifier.padding(paddingValues = it).padding(horizontal = 24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = "2023년 4월 20일", style = TwoTooTheme.typography.headLineNormal24)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cancel),
                        contentDescription = null,
                    )
                }
                TwoTooImageView(
                    model = "",
                    modifier = Modifier.fillMaxWidth().height(327.dp).padding(vertical = 24.dp).clip(TwoTooTheme.shape.extraSmall),
                )
                Text(
                    text = "30분이상 운동하기",
                    style = TwoTooTheme.typography.headLineNormal24,
                )
                Text(
                    text = "오늘 따라 너무 핀곤해서 힘들었다!!! 으악 ! \n 그래도 귀여운 꽃을 키우기 위해 열심히 해야지 ㅎㅎ",
                    modifier = Modifier.padding(top = 20.dp),
                    style = TwoTooTheme.typography.headLineNormal18,
                )
                Text(
                    text = "입력 시간: 20:35",
                    modifier = Modifier.padding(top = 20.dp),
                    style = TwoTooTheme.typography.headLineNormal18,
                    color = TwoTooTheme.color.mainPink,
                )

                Text(
                    text = "공주가 보낸 칭찬",
                    modifier = Modifier.padding(top = 33.dp),
                    style = TwoTooTheme.typography.bodyNormal16,
                    color = TwotooPink,
                )

                Box(
                    modifier = Modifier.padding(top = 8.dp).fillMaxWidth().height(46.dp).clip(TwoTooTheme.shape.extraSmall).background(TwoTooTheme.color.mainWhite),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "수고했어 오늘도",
                        style = TwoTooTheme.typography.bodyNormal16,
                        modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHistoryDetailScreen() {
    HistoryDetailScreen()
}
