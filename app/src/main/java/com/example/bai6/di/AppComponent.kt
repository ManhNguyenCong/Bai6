package com.example.bai6.di

import com.example.bai6.ui.MainActivity
import com.example.bai6.ui.fragment.ListUsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(fragment: ListUsersFragment)
    fun inject(activity: MainActivity)
}