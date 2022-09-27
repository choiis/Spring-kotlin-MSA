package org.api.menu

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MenuRepository : JpaRepository<MenuEntity?, Int> {

    fun findByMid(mid: String): Optional<MenuEntity>

    fun findByRestaurant_Rid(rid:Int) : List<MenuEntity>
}