package org.appkey.dao

import org.appkey.data.UserAppkey
import org.springframework.data.cassandra.repository.CassandraRepository

interface AppkeyRepository : CassandraRepository<UserAppkey?, String?>