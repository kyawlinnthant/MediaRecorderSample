package com.klt.voicerecordersamplecompose.screen

sealed class Events {
    data class ShowSnack(val status : PermissionStatus) : Events()
}