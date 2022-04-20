package com.klt.voicerecordersamplecompose.screen

sealed class Actions{
    data class ShowSnack(val status : PermissionStatus) : Actions()
}
