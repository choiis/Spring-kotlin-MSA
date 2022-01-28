package org.appkey.data

import lombok.*
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("sm01")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
class UserAppkey {

    @PrimaryKey
    var appkey: String? = null
    var userid: String? = null
    var usertype = 0
}