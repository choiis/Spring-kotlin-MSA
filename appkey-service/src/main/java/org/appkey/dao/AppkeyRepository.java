package org.appkey.dao;

import org.appkey.data.UserAppkey;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface AppkeyRepository extends CassandraRepository<UserAppkey,String> {

}
