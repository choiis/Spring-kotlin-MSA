package org.api.application.menu

open class MenuResponse{
    var mid: String? = null
    var rid: String? = null
    var name: String? = null
    var star: Int =0
    var cost: Int = 0

    constructor() {

    }

    constructor(mid: String?, rid: String?, name: String?, star: Int, cost: Int) {
        this.mid = mid
        this.rid = rid
        this.name = name
        this.star = star
        this.cost = cost
    }


}