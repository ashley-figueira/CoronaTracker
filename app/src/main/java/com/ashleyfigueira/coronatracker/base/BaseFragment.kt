package com.ashleyfigueira.coronatracker.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel<*>> : DaggerFragment() {

    @Inject lateinit var viewModelProvider: ViewModelProvider.Factory

    protected lateinit var viewModel: V

    protected lateinit var binding: B

    @LayoutRes abstract fun getLayoutResId(): Int

    fun getViewBinding(): B = binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initUI()
        return binding.root
    }

    abstract fun initUI()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<V>
        viewModel = ViewModelProvider(this, viewModelProvider).get(viewModelClass)
        viewModel.setLifeCycleOwner(this)
    }

    fun showErrorAsSnackbar(@StringRes messageId: Int) { view?.let { Snackbar.make(it, messageId, Snackbar.LENGTH_SHORT).show() } }

    fun navigateTo(navDirections: NavDirections) = findNavController().navigate(navDirections)

    fun navigateUp() = findNavController().navigateUp()
}