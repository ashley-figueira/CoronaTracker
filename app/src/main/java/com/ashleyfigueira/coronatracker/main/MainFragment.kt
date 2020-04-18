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
import com.ashleyfigueira.domain.entities.CountryStatsEntity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_country.view.*
import java.text.NumberFormat
import java.util.*

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

                    countryWideStatsSection.update(it.data.countryStats.map { CountryStatsItem(it) })
                }
            }
        })
    }

    inner class CountryStatsItem(private val countryStats: CountryStatsEntity) : Item() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) = viewHolder.itemView.bind()
        override fun getLayout(): Int = R.layout.item_country
        private fun View.bind() {
            countryName.text = countryStats.countryName
            newCasesCount.text = NumberFormat.getNumberInstance(Locale.US).format(countryStats.newCases)
            confirmedCount.text = NumberFormat.getNumberInstance(Locale.US).format(countryStats.totalCases)
            deathCount.text = NumberFormat.getNumberInstance(Locale.US).format(countryStats.totalDeaths)
            recoveredCount.text = NumberFormat.getNumberInstance(Locale.US).format(countryStats.totalRecovered)
        }

    }

}
