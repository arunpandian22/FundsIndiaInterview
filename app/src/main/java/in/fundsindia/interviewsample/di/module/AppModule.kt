package `in`.fundsindia.interviewsample.di.module;

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module;
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    // This tell Dagger to use App instance when required to inject Application
    // see more details here: https://google.github.io/dagger/api/2.22.1/dagger/Binds.html

//    @Provides
//    @Singleton
//    fun providesApplication(): Application = application
//
//    @Provides
//    @Singleton
//    fun providesApplicationContext(): Context = application

}