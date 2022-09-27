package org.api.restaurant

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RestaurantRepository : JpaRepository<RestaurantEntity?, Int> {

    fun findByName(name: String): Optional<RestaurantEntity>

}