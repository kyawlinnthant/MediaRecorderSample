package com.klt.voicerecordersamplecompose.sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun VoiceRecorderView(
    modifier: Modifier = Modifier,
    onLongPressListener: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.height(48.dp))
        Divider()
        VoiceRecorderContent(
            onLongPressListener = {

            }
        )
    }
}

@Composable
@Preview
private fun Preview() {
    Surface {
        VoiceRecorderView {

        }
    }
}