package org.api.restaurant

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name ="restaurant")
open class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var rid:Int = 0

    @Column(nullable = false, unique = true)
    var name: String? = null

    @Column(nullable = false)
    var location: String? = null

    var star:Int = 0

    @Column(nullable = false)
    var concept: String? = null
}