package com.example.siren.di.module

import com.example.siren.network.api.NaverApi
import com.example.siren.network.api.SirenApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NaverMap

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Local

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttpBuilder = OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
        return okhttpBuilder.build()
    }

    @Local
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://siren.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @NaverMap
    @Provides
    @Singleton
    fun provideNaverRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://naveropenapi.apigw.ntruss.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideSirenService(@Local retrofit: Retrofit): SirenApi =
        retrofit.create(SirenApi::class.java)

    @Singleton
    @Provides
    fun provideNaverService(@NaverMap retrofit: Retrofit): NaverApi =
        retrofit.create(NaverApi::class.java)
}