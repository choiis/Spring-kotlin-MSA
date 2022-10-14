package org.appkey.domain.user

import org.springframework.data.cassandra.repository.CassandraRepository

interface UserAppkeyRepository : CassandraRepository<UserAppkey?, String?>