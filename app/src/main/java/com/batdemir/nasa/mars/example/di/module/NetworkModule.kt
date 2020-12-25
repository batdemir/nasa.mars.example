package com.batdemir.nasa.mars.example.di.module

import com.batdemir.nasa.mars.example.BuildConfig
import com.batdemir.nasa.mars.example.data.remote.datasource.NasaRemoteDataSource
import com.batdemir.nasa.mars.example.data.remote.service.NasaService
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
    fun provideServiceNasa(retrofit: Retrofit): NasaService =
            retrofit.create(NasaService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSourceNasa(service: NasaService) =
            NasaRemoteDataSource(service)

    //////////////////////////////////////////////////////
}