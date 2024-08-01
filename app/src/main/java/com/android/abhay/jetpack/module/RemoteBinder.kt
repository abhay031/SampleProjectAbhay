package com.android.abhay.jetpack.module

import com.android.abhay.jetpack.remote.RecipeRemote
import com.android.abhay.jetpack.remote.RecipeRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RemoteBinder {

    @Singleton
    @Binds
    abstract fun bindRemote(remoteImpl: RecipeRemoteImpl): RecipeRemote

}