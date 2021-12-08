package com.nyt.nytimes.di

import com.nyt.nytimes.ui.main.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmModule = module {
    viewModel {
        MainActivityViewModel(get(), get())
    }
}