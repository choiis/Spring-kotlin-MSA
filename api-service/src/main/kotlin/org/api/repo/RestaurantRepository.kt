package org.api.repo

import org.api.data.Restaurant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RestaurantRepository : JpaRepository<Restaurant?, Int> {

    fun findByName(name: String): Optional<Restaurant>

}