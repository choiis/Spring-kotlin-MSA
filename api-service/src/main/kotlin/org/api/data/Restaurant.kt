package org.api.data

import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import javax.persistence.*

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name ="restaurant")
open class Restaurant {

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