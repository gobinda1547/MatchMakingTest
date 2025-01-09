package com.gobinda.connection.helper

import com.gobinda.connection.internal.ConnectionRole

private const val ICE_CANDIDATE_DB_PREFIX = "ice_"

fun whereToUploadIceCandidates(myRole: ConnectionRole): String {
    return ICE_CANDIDATE_DB_PREFIX + myRole.key
}

fun fromWhereToReceiveIceCandidates(myRole: ConnectionRole): String {
    return ICE_CANDIDATE_DB_PREFIX + when (myRole == ConnectionRole.Leader) {
        true -> ConnectionRole.Child.key
        else -> ConnectionRole.Leader.key
    }
}