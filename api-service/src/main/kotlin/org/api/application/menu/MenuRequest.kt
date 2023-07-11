package org.api.application.menu

open class MenuRequest {

    var rid: String? = null

    var name: String? = null

    var star:Int = 0

    var cost:Int = 0

    constructor() {

    }

    constructor(rid: String?, name: String?, star: Int, cost: Int) {
        this.rid = rid
        this.name = name
        this.star = star
        this.cost = cost
    }

}