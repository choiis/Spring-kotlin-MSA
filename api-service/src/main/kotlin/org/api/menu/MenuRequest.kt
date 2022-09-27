package org.api.menu

import lombok.Data
import lombok.NoArgsConstructor

@NoArgsConstructor
@Data
open class MenuRequest {

    val rid: Int = 0

    var name: String? = null

    var star:Int = 0

    var cost:Int = 0

}