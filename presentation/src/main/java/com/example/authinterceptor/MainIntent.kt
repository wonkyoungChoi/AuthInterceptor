package com.example.authinterceptor

import com.example.data.common.wrappers.SessionStatus
import com.example.domain.core.Result
import com.medium.client.common.wrappers.connectivity.NetworkStatus

data class MainViewState(
    val sessionStatus: Result<SessionStatus> = Result.Loading(),
    val networkStatus: NetworkStatus = NetworkStatus.CONNECTED
)