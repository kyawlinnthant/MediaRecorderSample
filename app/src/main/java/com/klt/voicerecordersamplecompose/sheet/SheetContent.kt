package com.klt.voicerecordersamplecompose.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klt.voicerecordersamplecompose.R

@Composable
fun VoiceRecorderContent(
    modifier: Modifier = Modifier,
    onLongPressListener: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = modifier.height(24.dp))
        Box(
            modifier = modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colors.error)
                .padding(all = 16.dp),
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
            onLongPressListener = {},
        )
    }
}