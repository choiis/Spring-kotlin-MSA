package org.api.restaurant

data class RestaurantResponse(
    val rid: Int,
    val name: String?,
    val location: String?,
    val star: Int,
    val concept: String?
) {

}