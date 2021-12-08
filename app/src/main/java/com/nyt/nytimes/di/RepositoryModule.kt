package com.nyt.nytimes.di


import com.nyt.nytimes.rest.NYTimesRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        NYTimesRepository()
    }
}