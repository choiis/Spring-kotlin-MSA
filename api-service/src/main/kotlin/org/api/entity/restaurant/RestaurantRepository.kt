package org.api.entity.restaurant

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RestaurantRepository : JpaRepository<RestaurantEntity?, String> {

    override fun findAll(page: Pageable): Page<RestaurantEntity?>
    fun findByName(name: String): Optional<RestaurantEntity>

}