package org.api.menu

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.api.restaurant.RestaurantEntity
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.*

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name ="menu")
open class MenuEntity {

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
    @Column(name = "mid", length = 36)
    var mid: String? = null


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rid", referencedColumnName = "rid")
    var restaurant: RestaurantEntity? = null

    @Column(nullable = false, unique = true)
    var name: String? = null

    var star:Int = 0

    var cost:Int = 0

}