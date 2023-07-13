package org.api.appkey


class UserAppkey {
    var appkey: String? = null
    var userid: String? = null
    var usertype = 0

    constructor() {

    }

    constructor(appkey: String?, userid: String?, usertype: Int) {
        this.appkey = appkey
        this.userid = userid
        this.usertype = usertype
    }

}