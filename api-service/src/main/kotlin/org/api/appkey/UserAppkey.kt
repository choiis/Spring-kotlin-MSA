package org.api.appkey

import lombok.*

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
class UserAppkey {
    var appkey: String? = null
    var userid: String? = null
    var usertype = 0

    override fun toString(): String {
        return "UserAppkey{" +
                "appkey='" + appkey + '\'' +
                ", userid='" + userid + '\'' +
                ", usertype=" + usertype +
                '}'
    }
}