package com.klt.voicerecordersamplecompose.screen

import android.Manifest
import android.media.MediaRecorder
import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.klt.voicerecordersamplecompose.MainViewModel
import com.klt.voicerecordersamplecompose.conversation.InputSection
import com.klt.voicerecordersamplecompose.sheet.VoiceRecorderView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun ConversationView(
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    val vm: MainViewModel = hiltViewModel()
    val permissionsState =
        rememberMultiplePermissionsState(permissions = listOf(Manifest.permission.RECORD_AUDIO))
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    var message by remember {
        mutableStateOf("")
    }
    val recorder : MediaRecorder =  remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
    }

    LaunchedEffect(key1 = true) {
        vm.event.collectLatest {
            when (it) {
                is Events.ShowSnack -> {
                    when (it.status) {
                        PermissionStatus.IS_GRANTED -> {
                            scaffoldState.snackbarHostState.showSnackbar(message = "Granted")
                        }
                        PermissionStatus.IS_PERMANENTLY_DENIED -> {
                            scaffoldState.snackbarHostState.showSnackbar(message = "Permanently Denied")

                        }
                        PermissionStatus.SHOULD_SHOW_RATIONALE -> {
                            scaffoldState.snackbarHostState.showSnackbar(message = "Should show rational")

                        }
                    }
                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetContent = {
            VoiceRecorderView(
                onStopPressed = {

                },
                onStartPressed = {

                }
            )

        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(
            topStart = 20.dp,
            topEnd = 20.dp,
        ),
        sheetBackgroundColor = MaterialTheme.colors.surface,
        scrimColor = Color.Black.copy(alpha = 0.9f)
    ) {
        Scaffold(scaffoldState = scaffoldState) {
            Surface {
                Column(
                    modifier = modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(all = 2.dp)
                            .weight(1f)
                    ) {
                        LazyColumn(
                            reverseLayout = true
                        ) {

                        }
                    }
                    Divider()
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        InputSection(
                            onAddClicked = {

                            },
                            onRecordClicked = {

                                if (permissionsState.allPermissionsGranted) {
                                    scope.launch {
                                        modalBottomSheetState.show()
                                    }
                                } else {
                                    permissionsState.launchMultiplePermissionRequest()
                                    permissionsState.permissions.forEach {
                                        when (it.permission) {
                                            Manifest.permission.RECORD_AUDIO -> {
                                                when {
                                                    it.isPermanentlyDenied() -> {
                                                        vm.onAction(Actions.ShowSnack(status = PermissionStatus.IS_PERMANENTLY_DENIED))
                                                    }
                                                    it.shouldShowRationale -> {
                                                        vm.onAction(Actions.ShowSnack(status = PermissionStatus.SHOULD_SHOW_RATIONALE))
                                                    }
                                                    it.hasPermission -> {
                                                        vm.onAction(Actions.ShowSnack(status = PermissionStatus.IS_GRANTED))
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            },
                            chatMessage = message,
                            onChatMessageChanged = {
                                message = it
                            },
                            chatPlaceholderMessage = "Type here",
                        )
                    }
                }

            }
        }
    }
}


@ExperimentalPermissionsApi
fun PermissionState.isPermanentlyDenied(): Boolean {
    return !shouldShowRationale && !hasPermission
}
