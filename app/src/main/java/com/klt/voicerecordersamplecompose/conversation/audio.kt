package com.klt.voicerecordersamplecompose.conversation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klt.voicerecordersamplecompose.R

@Composable
fun SenderConversationAudio(
    modifier: Modifier = Modifier,
    duration: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 64.dp,
                end = 16.dp,
                top = 2.dp,
                bottom = 2.dp
            ),
        contentAlignment = Alignment.CenterEnd
    ) {
        Row(
            modifier = modifier
                .clip(
                    RoundedCornerShape(
                        topEnd = 16.dp,
                        topStart = 16.dp,
                        bottomStart = 16.dp,
                    )
                )
                .background(color = MaterialTheme.colors.primary)
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(id = R.drawable.ic_chat_audio),
                contentDescription = "audio",
                tint = MaterialTheme.colors.surface
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = duration,
                color = MaterialTheme.colors.surface,
                textAlign = TextAlign.End
            )
        }
    }
}

@Composable
@Preview
private fun SenderAudioPreview() {
    Surface {
        SenderConversationAudio(duration = "01:09")
    }
}
