package com.mashup.twotoo.presenter.designsystem.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideImageState

@Composable
fun TwoTooImageView(
    model: Any?,
    modifier: Modifier = Modifier,
    @DrawableRes previewPlaceholder: Int? = null,
    loadingPlaceHolder: @Composable (BoxScope.(GlideImageState.Loading) -> Unit) = {},
    failurePlaceHolder: @Composable (BoxScope.(GlideImageState.Failure) -> Unit) = {},
) {
    TwoTooImageViewImpl(
        model = model,
        previewPlaceholder = previewPlaceholder,
        loadingPlaceHolder = loadingPlaceHolder,
        failurePlaceHolder = failurePlaceHolder,
        modifier = modifier,
    )
}

@Composable
fun TwoTooImageViewWithSetter(
    modifier: Modifier = Modifier,
    imageUri: Uri? = null,
    onClickSetImage: () -> Unit = {},
    @DrawableRes previewPlaceholder: Int? = null,
    loadingPlaceHolder: @Composable (BoxScope.(GlideImageState.Loading) -> Unit) = {},
    failurePlaceHolder: @Composable (BoxScope.(GlideImageState.Failure) -> Unit) = {},
) {
    TwoTooImageViewImpl(
        model = imageUri,
        enableSetImage = true,
        onClickSetImage = onClickSetImage,
        previewPlaceholder = previewPlaceholder,
        loadingPlaceHolder = loadingPlaceHolder,
        failurePlaceHolder = failurePlaceHolder,
        modifier = modifier,
    )
}

@Composable
fun TwoTooImageViewImpl(
    model: Any?,
    modifier: Modifier = Modifier,
    @DrawableRes previewPlaceholder: Int? = null,
    onClickSetImage: (() -> Unit) = {},
    enableSetImage: Boolean = false,
    loadingPlaceHolder: @Composable (BoxScope.(GlideImageState.Loading) -> Unit) = {},
    failurePlaceHolder: @Composable (BoxScope.(GlideImageState.Failure) -> Unit) = {},

) {
    Box(
        modifier = modifier,
    ) {
        GlideImage(
            modifier = Modifier.fillMaxSize(),
            imageModel = { model ?: R.drawable.empty_image_color_placeholder },
            previewPlaceholder = previewPlaceholder ?: R.drawable.empty_image_color_placeholder,
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            ),
            loading = loadingPlaceHolder,
            failure = failurePlaceHolder,
        )
        if (enableSetImage) {
            PlusLine(
                modifier = Modifier
                    .fillMaxSize(0.5f)
                    .align(Alignment.Center)
                    .clickable {
                        onClickSetImage.invoke()
                    },

            )
        }
    }
}

@Composable
fun PlusLine(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            // Fetching width and height for
            // setting start x and end y
            val canvasWidth = size.width
            val canvasHeight = size.height

            // drawing a line between start(x,y) and end(x,y)
            drawLine(
                start = Offset(x = (canvasWidth / 2) - (canvasWidth / 7), y = (canvasHeight / 2)),
                end = Offset(x = (canvasWidth / 2) + (canvasWidth / 7), y = (canvasHeight / 2)),
                color = Color.White,
                cap = StrokeCap.Round,
                strokeWidth = 4.dp.toPx(),
            )
            drawLine(
                start = Offset(x = (canvasWidth / 2), y = (canvasHeight / 2) - (canvasHeight / 7)),
                end = Offset(x = (canvasWidth / 2), y = (canvasHeight / 2) + (canvasHeight / 7)),
                color = Color.White,
                cap = StrokeCap.Round,
                strokeWidth = 4.dp.toPx(),
            )
        }
    }
}

@Preview(
    name = "이미지가 없을 때",
    showBackground = true,
)
@Composable
fun HasNoneImageView() {
    TwoTooTheme {
        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }

        val launcher = rememberLauncherForActivityResult(
            contract =
            ActivityResultContracts.GetContent(),
        ) { uri: Uri? ->
            imageUri = uri
        }

        TwoTooImageViewWithSetter(
            modifier = Modifier
                .size(250.dp)
                .clip(
                    TwoTooTheme.shape.extraSmall,
                )
                .border(width = 1.dp, color = Color.LightGray, shape = TwoTooTheme.shape.extraSmall),
            previewPlaceholder = R.drawable.empty_image_color_placeholder,
            imageUri = imageUri,
            onClickSetImage = {
                launcher.launch("image/*")
            },
        )
    }
}

@Preview(
    name = "이미지가 있을 때",
    showBackground = true,
)
@Composable
fun HasImageView() {
    TwoTooTheme {
        TwoTooImageView(
            modifier = Modifier
                .size(250.dp)
                .clip(
                    TwoTooTheme.shape.extraSmall,
                )
                .border(width = 1.dp, color = Color.LightGray, shape = TwoTooTheme.shape.extraSmall),
            model = null,
        )
    }
}

@Preview(
    name = "라운드 처리 없는 이미지",
    showBackground = true,
)
@Composable
fun ImageViewWithNoneRound() {
    TwoTooTheme {
        TwoTooImageView(
            modifier = Modifier.size(250.dp),
            model = null,
        )
    }
}

@Preview("플러스 버튼")
@Composable
fun PlusButton() {
    TwoTooTheme {
        PlusLine(
            modifier = Modifier.size(200.dp),
        )
    }
}
