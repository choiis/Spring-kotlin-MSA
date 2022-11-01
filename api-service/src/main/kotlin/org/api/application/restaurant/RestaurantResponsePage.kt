package org.api.application.restaurant

data class RestaurantResponsePage(
        val list: List<RestaurantResponse>,
        val size: Int,
        val page: Int
) {

}