import com.dttden.api.model.Hero
import com.dttden.hero.database.HeroEntity

object HeroEntityMapper {
    fun toEntity(year: Int, hero: Hero): HeroEntity {
        return HeroEntity(hero.name, hero.picture, hero.score, year)
    }

    fun toVO(heroEntity: HeroEntity): Hero {
        return Hero(heroEntity.name, heroEntity.picture, heroEntity.score)
    }
}
