package org.api.restaurant

import lombok.Data
import lombok.NoArgsConstructor

@NoArgsConstructor
@Data
open class RestaurantRequest {

    var rid:Int = 0

    var name: String? = null

    var location: String? = null

    var star:Int = 0

    var concept: String? = null
}