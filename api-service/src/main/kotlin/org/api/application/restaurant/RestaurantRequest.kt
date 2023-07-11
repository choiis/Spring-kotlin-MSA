package org.api.application.restaurant

open class RestaurantRequest {

    var rid:String? = null

    var name: String? = null

    var location: String? = null

    var star:Int = 0

    var concept: String? = null

    constructor() {

    }

    constructor(rid: String?, name: String?, location: String?, star: Int, concept: String?) {
        this.rid = rid
        this.name = name
        this.location = location
        this.star = star
        this.concept = concept
    }

}