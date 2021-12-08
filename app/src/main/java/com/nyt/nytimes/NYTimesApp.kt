package com.nyt.nytimes

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import org.koin.android.ext.koin.androidContext
import com.nyt.nytimes.di.appModule
import com.nyt.nytimes.di.repoModule
import com.nyt.nytimes.di.vmModule
import org.koin.core.context.startKoin


class NYTimesApp : Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Context
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@NYTimesApp)
            modules(
                listOf(
                    appModule, vmModule, repoModule
                )
            )
        }
    }
}