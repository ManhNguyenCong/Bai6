package com.example.bai6.di

import android.app.Application
import dagger.Component
import dagger.internal.DaggerCollections
import dagger.internal.DaggerGenerated

class ExerciseApplication: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}