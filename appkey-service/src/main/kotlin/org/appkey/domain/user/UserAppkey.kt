package org.appkey.domain.user

import lombok.*
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("sm01")
@AllArgsConstructor
@Data
class UserAppkey {

    @PrimaryKey
    var appkey: String? = null
    var userid: String? = null
    var usertype = 0
}