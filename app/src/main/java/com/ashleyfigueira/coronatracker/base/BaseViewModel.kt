package com.ashleyfigueira.coronatracker.base

import androidx.lifecycle.*
import com.ashleyfigueira.domain.common.CoronaError
import com.ashleyfigueira.coronatracker.R

abstract class BaseViewModel<V : ScreenState<*>> : ViewModel(), DefaultLifecycleObserver {

    protected val _screenState = MutableLiveData<V>()
    val screenState: LiveData<V> get() = _screenState

    fun setLifeCycleOwner(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    fun getErrorMessage(error: CoronaError): Int {
        return when (error) {
            is CoronaError.Offline -> R.string.error_offline_message
            is CoronaError.Timeout -> R.string.error_timeout_message
            is CoronaError.Unknown -> R.string.error_loading_failed_message
            else -> R.string.error_loading_failed_message
        }
    }
}