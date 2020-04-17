package com.ashleyfigueira.coronatracker

import com.ashleyfigueira.coronatracker.base.BaseActivity
import com.ashleyfigueira.coronatracker.common.CoronaApplication.Companion.appComponent
import com.ashleyfigueira.coronatracker.di.DaggerMainComponent

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int = R.layout.activity_main
    override fun onInject() {
        DaggerMainComponent.builder()
            .core(appComponent)
            .activity(this)
            .context(this)
            .build()
            .inject(this)
    }
}
