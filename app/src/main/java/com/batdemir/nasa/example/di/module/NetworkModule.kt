package com.batdemir.nasa.example.di.module

import com.batdemir.nasa.example.BuildConfig
import com.batdemir.nasa.example.data.remote.datasource.MySearchParams
import com.batdemir.nasa.example.data.remote.datasource.NasaPagingDataSource
import com.batdemir.nasa.example.data.remote.service.NasaPagingService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {
    private const val BASE_URL = "https://api.nasa.gov"
    const val API_KEY = "qTL1f8gLphjSLf4phUG83RLikffrUhpYTbQsceQI"

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG)
            okHttpClientBuilder.addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(client)
            .build()
    }

    //////////////////////////////////////////////////////

    @Singleton
    @Provides
    fun provideServiceNasaPaging(retrofit: Retrofit): NasaPagingService =
        retrofit.create(NasaPagingService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSourceNasaPaging(
        service: NasaPagingService,
        searchParams: MySearchParams
    ) =
        NasaPagingDataSource(service, searchParams)

    //////////////////////////////////////////////////////
}