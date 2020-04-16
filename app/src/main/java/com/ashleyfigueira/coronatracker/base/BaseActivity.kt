package com.ashleyfigueira.coronatracker.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject lateinit var injector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = injector

    @LayoutRes abstract fun getLayoutResId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        onInject()
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }

    abstract fun onInject()
}