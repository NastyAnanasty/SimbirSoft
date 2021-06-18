package com.kas.simbirsoft.di.component

import com.kas.simbirsoft.di.module.DataModule
import com.kas.simbirsoft.presenter.viewModel.BusinessViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules =[DataModule::class])
interface AppComponent {
    fun inject(viewModel: BusinessViewModel)
}