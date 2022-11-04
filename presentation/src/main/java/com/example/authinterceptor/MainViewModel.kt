package com.example.authinterceptor

import androidx.lifecycle.viewModelScope
import com.example.authinterceptor.base.BaseViewModel
import com.example.data.common.wrappers.SessionManager
import com.example.domain.core.Result
import com.medium.client.common.wrappers.connectivity.NetworkConnectivityManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val networkConnectivityManager: NetworkConnectivityManager
) : BaseViewModel<MainViewState, Unit, Unit>(MainViewState()) {

    init {
        viewModelScope.launch {
            setState { copy(sessionStatus = Result.Loading()) }
            sessionManager.observeSessionStatus().collect { sessionStatus ->
                setState { copy(sessionStatus = Result.Success(sessionStatus)) }
            }
        }

        viewModelScope.launch {
            networkConnectivityManager.observeNetworkStatus().collect { networkStatus ->
                setState { copy(networkStatus = networkStatus) }
            }
        }
    }

    override fun onEvent(event: Unit) = Unit
}