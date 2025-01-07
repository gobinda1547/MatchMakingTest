package com.example.matchmakingtest.ui.screen.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchmakingtest.app.ROOM_ID
import com.example.matchmakingtest.connection.SignalingManager
import com.example.matchmakingtest.connection.WebRTCManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val signalingManager: SignalingManager,
    private val webRTCManager: WebRTCManager
) : ViewModel() {

    val roomId = ROOM_ID

    fun handleSendOffer() {
        viewModelScope.launch(Dispatchers.IO) {
            signalingManager.listenForAnswer(roomId) { answer ->
                webRTCManager.handleAnswer(answer)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            signalingManager.listenForCandidates(roomId, "r") { candidate ->
                webRTCManager.handleCandidate(roomId, candidate)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            webRTCManager.createPeerConnection("s")
            webRTCManager.createOffer(roomId)
        }
    }

    fun waitAndSee() {
        viewModelScope.launch(Dispatchers.IO) {
            signalingManager.listenForOffer(roomId) { offer ->
                webRTCManager.createAnswer(roomId, offer)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            signalingManager.listenForCandidates(roomId, "s") { candidate ->
                webRTCManager.handleCandidate(roomId, candidate)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            webRTCManager.createPeerConnection("r")
        }
    }

}