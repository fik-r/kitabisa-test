package com.example.kitabisa_assesment

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.kitabisa_assesment.utils.CoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MainInject {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context) = OkHttpClient.Builder().apply {
        if (BuildConfig.DEBUG) addInterceptor(ChuckerInterceptor(context))
    }.build()

    @Singleton
    @Provides
    fun provideCoroutinesDispatcher() = CoroutinesDispatcherProvider(
        main = Dispatchers.Main,
        computation = Dispatchers.Default,
        io = Dispatchers.IO
    )
}