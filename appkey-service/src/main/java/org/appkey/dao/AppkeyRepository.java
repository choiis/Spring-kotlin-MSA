package org.appkey.dao;

import org.appkey.data.Sm01vo;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface AppkeyRepository extends CassandraRepository<Sm01vo,String> {

}
