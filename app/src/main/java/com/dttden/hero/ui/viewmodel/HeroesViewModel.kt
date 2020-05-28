package com.dttden.hero.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dttden.api.model.Hero
import com.dttden.hero.data.HeroesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HeroesViewModel @Inject constructor(private val repository: HeroesRepository) : ViewModel() {

    private val TAG = HeroesViewModel::class.simpleName
    private val years = intArrayOf(2006, 2009, 2012, 2015, 2018)
    private val currentYear = MutableLiveData(2006)
    private var currentPage = -1

    fun loadHeroes() {
        findCurrentPage()
        Log.d(TAG, "loadHeroes Year " + currentYear())
        CoroutineScope(Dispatchers.IO).launch {
            repository.loadHeroes(currentYear())
        }
    }

    fun getHeroes(): LiveData<List<Hero>> {
        Log.d(TAG, "getHeroes Year " + currentYear())
        return repository.getHeroes(currentYear())
    }

    fun getCurrentYear(): LiveData<Int> {
        return currentYear
    }

    private fun currentYear(): Int {
        return years[currentPage]
    }

    private fun findCurrentPage() {
        if ((currentPage + 1) >= years.size) {
            currentPage = 0
        } else {
            currentPage++
        }
        currentYear.postValue(years[currentPage])
    }


}
