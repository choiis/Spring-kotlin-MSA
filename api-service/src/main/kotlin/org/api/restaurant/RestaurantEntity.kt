package org.api.restaurant

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name ="restaurant")
open class RestaurantEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator",
        parameters = [Parameter(
            name = "uuid_gen_strategy_class",
            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
        )]
    )
    @Column(name = "rid", length = 36)
    var rid:String? = null

    @Column(nullable = false, unique = true)
    var name: String? = null

    @Column(nullable = false)
    var location: String? = null

    var star:Int = 0

    @Column(nullable = false)
    var concept: String? = null
}