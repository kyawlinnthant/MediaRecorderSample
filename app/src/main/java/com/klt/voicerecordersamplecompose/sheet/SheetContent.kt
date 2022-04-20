package com.klt.voicerecordersamplecompose.sheet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klt.voicerecordersamplecompose.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VoiceRecorderContent(
    modifier: Modifier = Modifier,
    onStartPressed: () -> Unit,
    onStopPressed: () -> Unit,
) {

    var longPressActive by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = modifier.height(24.dp))
        Box(
            modifier = modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(
                    color = if (longPressActive) MaterialTheme.colors.error.copy(alpha = 0.2f) else
                        MaterialTheme.colors.error
                )
                .padding(all = 16.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            longPressActive = true
                            onStartPressed()
                        },
                        onPress = {
                            awaitRelease()
                            longPressActive = false
                            onStopPressed()
                        }

                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_mic),
                contentDescription = "record",
                tint = MaterialTheme.colors.surface,
                modifier = modifier.fillMaxSize()
            )
        }
        Spacer(modifier = modifier.height(8.dp))
        Text(text = "Hold and record")
        Spacer(modifier = modifier.height(24.dp))
    }
}

@Composable
@Preview
private fun Preview() {
    Surface {
        VoiceRecorderContent(
            onStartPressed = {},
            onStopPressed = {}
        )
    }
}