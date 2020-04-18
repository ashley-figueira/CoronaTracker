package com.ashleyfigueira.coronatracker.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashleyfigueira.coronatracker.R
import com.ashleyfigueira.coronatracker.base.BaseFragment
import com.ashleyfigueira.coronatracker.base.ScreenState
import com.ashleyfigueira.coronatracker.common.gone
import com.ashleyfigueira.coronatracker.common.visible
import com.ashleyfigueira.coronatracker.databinding.FragmentMainBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private val worldStatsSection = Section().apply { setHideWhenEmpty(true) }
    private val countryWideStatsSection = Section().apply { setHideWhenEmpty(true) }

    init {
        groupAdapter.add(worldStatsSection)
        groupAdapter.add(countryWideStatsSection)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_main

    override fun initUI() {
        with (binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ScreenState.Loading -> binding.refreshLayout.isRefreshing = it.isLoading
                is ScreenState.Error -> showErrorAsSnackbar(it.errorMessage)
                is ScreenState.NoInternet -> {
                    binding.emptyView.gone()
                    binding.noInternetView.visible()
                    binding.recyclerView.gone()
                }
                is ScreenState.Empty -> {
                    binding.noInternetView.gone()
                    binding.emptyView.visible()
                    binding.recyclerView.gone()
                }
                is ScreenState.Success -> {
                    binding.noInternetView.gone()
                    binding.emptyView.gone()
                    binding.recyclerView.visible()


                }
            }
        })
    }

}
