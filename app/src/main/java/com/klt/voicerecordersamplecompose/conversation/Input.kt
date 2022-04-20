package com.klt.voicerecordersamplecompose.conversation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klt.voicerecordersamplecompose.R

@Composable
fun InputSection(
    modifier: Modifier = Modifier,
    onAddClicked: () -> Unit,
    onRecordClicked: () -> Unit,
    chatMessage: String,
    onChatMessageChanged: (String) -> Unit,
    chatPlaceholderMessage: String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colors.onBackground.copy(alpha = 0.09f)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = modifier.width(8.dp))
        IconButton(onClick = onAddClicked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add",
                tint = MaterialTheme.colors.onSurface
            )
        }

        BasicTextField(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(
                    vertical = 16.dp,
                    horizontal = 4.dp
                )
                .weight(1f),
            value = chatMessage,
            textStyle = TextStyle(
                color = MaterialTheme.colors.onSurface
            ),
            onValueChange = onChatMessageChanged,
            decorationBox = { innerTextField ->
                if (chatMessage.isEmpty()) {
                    Text(
                        text = chatPlaceholderMessage,
                        color = MaterialTheme.colors.onSurface.copy(0.7f),
                    )
                }
                innerTextField()
            },
            singleLine = false,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            cursorBrush = SolidColor(
                value = MaterialTheme.colors.primary
            ),
        )

        IconButton(onClick = onRecordClicked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_mic),
                contentDescription = "record",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Composable
@Preview
private fun MultiplePreview() {
    Surface {
        InputSection(
            onAddClicked = { },
            onRecordClicked = { },
            chatMessage = "as;ldkfjaslfjl;asjfl;sajfl;sjf;lasjfl;sadjfl;sdjfl;sjfa" +
                    "alskjfl;sadjfl;sdjf;lsafjasl;kdfjl;sadfjl;sdjf" +
                    "lasdjfl;ksadjfl;sadjfl;asjf;lsdajfl;sadfj" +
                    "salkdfjl;sdjfl;ksdjfl;sadf",
            onChatMessageChanged = {},
            chatPlaceholderMessage = "Type message"
        )
    }
}

@Composable
@Preview
private fun SinglePreview() {
    Surface {
        InputSection(
            onAddClicked = { },
            onRecordClicked = { },
            chatMessage = "",
            onChatMessageChanged = {},
            chatPlaceholderMessage = "Type message"
        )
    }
}