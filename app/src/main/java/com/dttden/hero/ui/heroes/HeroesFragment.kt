package com.dttden.hero.ui.heroes

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dttden.api.model.Hero
import com.dttden.hero.R
import com.dttden.hero.di.ViewModelFactory
import com.dttden.hero.ui.viewmodel.HeroesViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_heroes.*
import javax.inject.Inject

class HeroesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var heroesAdapter: HeroesAdapter
    private lateinit var heroesViewModel: HeroesViewModel
    private lateinit var heroesLiveData: LiveData<List<Hero>>
    private val TAG = HeroesFragment::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        heroesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HeroesViewModel::class.java)
        createAdapter()
        heroesViewModel.loadHeroes()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_heroes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heroesViewModel.getCurrentYear().observe(this, Observer { currentYear ->
            year.text = currentYear.toString()
        })
        observeHeroes()
        setupAdapter()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            onRefresh()
        }
    }

    private fun setupAdapter() {
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(itemDecorator)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = heroesAdapter
    }

    private fun createAdapter() {
        heroesAdapter = HeroesAdapter(requireContext(), object : HeroesAdapter.OnItemClickListener {
            override fun onItemClicked(hero: Hero) {
                Log.d(TAG, "Hero ${hero.name}")
            }
        })
    }

    private fun observeHeroes() {
        heroesLiveData = heroesViewModel.getHeroes()
        heroesLiveData.observe(this, Observer { heroes ->
            heroesAdapter.setHeroes(heroes)
        })
    }

    private fun onRefresh() {
        heroesLiveData.removeObservers(this)
        heroesViewModel.loadHeroes()
        observeHeroes()
    }
}
