package org.api.application.restaurant

data class RestaurantResponse(
    val rid: String?,
    val name: String?,
    val location: String?,
    val star: Int,
    val concept: String?
) {

}