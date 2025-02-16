package com.example.matchmakingtest.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.matchmakingtest.ui.models.GameScreenUiAction

@Composable
fun ChatInputDesign(onUiAction: (GameScreenUiAction) -> Unit) {
    val message = remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            InputFieldBasicPart(
                textNow = message.value,
                onTextChanged = { message.value = it }
            )
        }

        IconButton(onClick = {
            onUiAction(GameScreenUiAction.SendMessage(message.value))
            message.value = ""
        }) {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = "Send"
            )
        }
    }
}