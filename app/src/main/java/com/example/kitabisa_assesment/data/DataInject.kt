package com.example.kitabisa_assesment.data

import android.content.Context
import androidx.room.Room
import com.example.kitabisa_assesment.data.api.AppService
import com.example.kitabisa_assesment.data.database.AppDatabase
import com.example.kitabisa_assesment.data.database.dao.UniversityDao
import com.example.kitabisa_assesment.data.datasource.AppLocalDataSourceImpl
import com.example.kitabisa_assesment.data.datasource.AppRemoteDataSourceImpl
import com.example.kitabisa_assesment.data.repository.AppRepositoryImpl
import com.example.kitabisa_assesment.domain.abstraction.datasource.AppLocalDataSource
import com.example.kitabisa_assesment.domain.abstraction.datasource.AppRemoteDataSource
import com.example.kitabisa_assesment.domain.abstraction.repository.AppRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataInject {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "kitabisa"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUniversityDao(appDatabase: AppDatabase): UniversityDao {
        return appDatabase.universityDao()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideAppService(retrofit: Retrofit) = retrofit.create(AppService::class.java)

    @Singleton
    @Provides
    fun provideAppRemoteDataSource(
        appService: AppService
    ): AppRemoteDataSource = AppRemoteDataSourceImpl(
        appService
    )

    @Singleton
    @Provides
    fun provideAppLocalDataSource(dao: UniversityDao): AppLocalDataSource =
        AppLocalDataSourceImpl(dao)

    @Singleton
    @Provides
    fun provideAppRepository(
        appRemoteDataSource: AppRemoteDataSource,
        appLocalDataSource: AppLocalDataSource
    ): AppRepository = AppRepositoryImpl(appRemoteDataSource, appLocalDataSource)
}