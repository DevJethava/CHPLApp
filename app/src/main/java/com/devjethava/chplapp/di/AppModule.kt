package com.devjethava.chplapp.di

import com.devjethava.chplapp.BuildConfig
import com.devjethava.chplapp.database.AppDatabase
import com.devjethava.chplapp.database.repository.UserRepository
import com.devjethava.chplapp.helper.Preference
import com.devjethava.chplapp.helper.ResponseInterceptor
import com.devjethava.chplapp.model.remote.RestApiService
import com.devjethava.chplapp.model.repository.ApiRepository
import com.devjethava.chplapp.model.repository.ApiRepositoryImpl
import com.devjethava.chplapp.viewmodel.DashboardViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val apiModule = module {

    fun provideRestApi(retrofit: Retrofit): RestApiService {
        return retrofit.create(RestApiService::class.java)
    }
    singleOf(::provideRestApi)
    singleOf(::Preference)
}

val viewModelModule = module {
    viewModelOf(::DashboardViewModel)
}

val repositoryModule = module {

    fun provideAPIRepository(api: RestApiService): ApiRepository {
        return ApiRepositoryImpl(api)
    }
    singleOf(::provideAPIRepository)

    // Room Database Repository
    singleOf(::UserRepository)
}

val networkModule = module {
    val connectTimeout: Long = 180// 20s
    val readTimeout: Long = 180 // 20s

    /*
    Http logging client
     */
    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder =
            OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (true) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.addInterceptor(ResponseInterceptor())
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    /*
    Retrofit for API
     */
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(client).build()
    }

    singleOf(::provideHttpClient)
    singleOf(::provideRetrofit)
}

val roomDatabaseModule = module {

    // Room Database
    single {
        AppDatabase.getInstance(androidContext())
    }

    single { get<AppDatabase>().userDAO() }
}

val appModule =
    listOf(apiModule, repositoryModule, viewModelModule, networkModule, roomDatabaseModule)