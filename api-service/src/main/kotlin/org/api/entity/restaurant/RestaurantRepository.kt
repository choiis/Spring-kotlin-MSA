package org.api.entity.restaurant

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RestaurantRepository : JpaRepository<RestaurantEntity?, String> {

    fun findByName(name: String): Optional<RestaurantEntity>

}