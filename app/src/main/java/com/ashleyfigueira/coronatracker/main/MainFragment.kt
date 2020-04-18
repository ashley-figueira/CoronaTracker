package com.ashleyfigueira.coronatracker.main

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashleyfigueira.coronatracker.R
import com.ashleyfigueira.coronatracker.base.BaseFragment
import com.ashleyfigueira.coronatracker.base.ScreenState
import com.ashleyfigueira.coronatracker.common.gone
import com.ashleyfigueira.coronatracker.common.setWeight
import com.ashleyfigueira.coronatracker.common.visible
import com.ashleyfigueira.coronatracker.databinding.FragmentMainBinding
import com.ashleyfigueira.domain.entities.CountryStatsEntity
import com.ashleyfigueira.domain.entities.WorldStatsEntity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_country.view.*
import kotlinx.android.synthetic.main.item_country.view.confirmedCount
import kotlinx.android.synthetic.main.item_country.view.deathCount
import kotlinx.android.synthetic.main.item_country.view.recoveredCount
import kotlinx.android.synthetic.main.item_title.view.*
import kotlinx.android.synthetic.main.item_worldstats.view.*
import java.text.NumberFormat
import java.util.*

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    private val worldStatsSection = Section().apply {
        setHeader(TitleItem(R.string.stat_heading))
        setHideWhenEmpty(true)
    }

    private val countryWideStatsSection = Section().apply {
        setHeader(TitleItem(R.string.list_heading))
        setHideWhenEmpty(true)
    }

    init {
        groupAdapter.add(worldStatsSection)
        groupAdapter.add(countryWideStatsSection)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_main

    override fun initUI() {
        binding.refreshLayout.isEnabled = false
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

                    worldStatsSection.update(listOf(WorldStatsItem(it.data.worldStats)))
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

    inner class TitleItem(@StringRes private val titleRes: Int) : Item() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) = viewHolder.itemView.bind()
        override fun getLayout(): Int = R.layout.item_title
        private fun View.bind() { title.text = getString(titleRes) }
    }

    inner class WorldStatsItem(private val worldStats: WorldStatsEntity) : Item() {
        override fun bind(viewHolder: GroupieViewHolder, position: Int) = viewHolder.itemView.bind()
        override fun getLayout(): Int = R.layout.item_worldstats
        private fun View.bind() {
            val weight = provideBarWeights(worldStats)
            yellowBar.setWeight(weight.first)
            greenBar.setWeight(weight.second)
            redBar.setWeight(weight.third)
            barContainer.weightSum = weight.first + weight.second + weight.third
            confirmedCount.text = NumberFormat.getNumberInstance(Locale.US).format(worldStats.totalCases)
            recoveredCount.text = NumberFormat.getNumberInstance(Locale.US).format(worldStats.totalRecovered)
            deathCount.text = NumberFormat.getNumberInstance(Locale.US).format(worldStats.totalDeaths)
        }

        private fun provideBarWeights(response: WorldStatsEntity): Triple<Float, Float, Float> {
            var yellowVal = response.totalCases.toFloat()
            var greenVal = response.totalRecovered.toFloat()
            var redVal = response.totalDeaths.toFloat()
            while (yellowVal > 1f && greenVal > 1f && redVal > 1f) { yellowVal /= 2f; greenVal /= 2f; redVal /= 2f }
            return Triple(yellowVal, greenVal, redVal)
        }
    }
}
