//package com.android.abhay.jetpack.module
//
//import android.content.Context
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@InstallIn(SingletonComponent::class)
//@Module
//object DatabaseBinder {
//
//    @Singleton
//    @Provides
//    fun provideDataBase(@ApplicationContext context: Context) : AppDatabase{
//        return AppDatabase.getInstance(context)
//    }
//
//    @Singleton
//    @Provides
//    fun provideMemberDao(appDatabase: AppDatabase): MemberDao = appDatabase.memberDao()
//
//}