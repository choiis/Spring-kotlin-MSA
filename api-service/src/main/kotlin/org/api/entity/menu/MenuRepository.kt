package org.api.entity.menu

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MenuRepository : JpaRepository<MenuEntity?, String> {

    fun findByMid(mid: String): Optional<MenuEntity>

    fun findByRestaurant_Rid(rid: String, page: Pageable): Page<MenuEntity>
}