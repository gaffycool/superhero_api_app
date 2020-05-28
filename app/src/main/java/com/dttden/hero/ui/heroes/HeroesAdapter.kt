package com.dttden.hero.ui.heroes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dttden.api.model.Hero
import com.dttden.hero.R
import com.dttden.hero.databinding.ListItemHeroBinding

class HeroesAdapter(private val context: Context, val clickListener: OnItemClickListener) :
    RecyclerView.Adapter<HeroesAdapter.ViewHolder>() {

    private val heroes = ArrayList<Hero>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_item_hero, parent, false)
        return ViewHolder(DataBindingUtil.bind(itemView)!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(heroes[position])
    }

    fun setHeroes(heroes: List<Hero>) {
        this.heroes.clear()
        this.heroes.addAll(heroes)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return this.heroes.size
    }

    inner class ViewHolder(private val heroBinding: ListItemHeroBinding) :
        RecyclerView.ViewHolder(heroBinding.root) {

        fun bind(Hero: Hero) {
            heroBinding.hero = Hero
            heroBinding.itemClickListener = clickListener
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(hero: Hero)
    }
}
