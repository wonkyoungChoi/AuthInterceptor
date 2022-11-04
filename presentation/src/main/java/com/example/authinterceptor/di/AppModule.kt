package com.example.authinterceptor.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.authinterceptor.R
import com.example.data.common.annotations.Host
import com.example.data.common.annotations.Port
import com.example.data.common.wrappers.SessionManager
import com.example.data.common.wrappers.SessionManagerImpl
import com.example.data.local.AppDataStore
import com.example.domain.repository.AuthRepository
import com.medium.client.common.wrappers.connectivity.NetworkConnectivityManager
import com.medium.client.common.wrappers.connectivity.NetworkConnectivityManagerImpl
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideSessionManager(
        appDataStore: AppDataStore,
        authRepository: Lazy<AuthRepository>
    ): SessionManager = SessionManagerImpl(
        appDataStore = appDataStore,
        authRepository = authRepository
    )

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
        context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager

    @Singleton
    @Provides
    fun provideNetworkConnectivityManager(
        connectivityManager: ConnectivityManager
    ): NetworkConnectivityManager = NetworkConnectivityManagerImpl(
        connectivityManager = connectivityManager
    )
}
