package org.appkey.service

import org.springframework.beans.factory.annotation.Autowired
import org.appkey.dao.AppkeyRepository
import org.appkey.data.UserAppkey
import org.springframework.stereotype.Service

@Service
class AppKeyService {

    @Autowired
    private lateinit var repository: AppkeyRepository

    fun findByPk(appkey: String): UserAppkey {
        return repository.findById(appkey).get()
    }

    fun save(vo: UserAppkey): UserAppkey? {
        return repository.save(vo)
    }

    fun delete(vo: UserAppkey) {
        repository.delete(vo)
    }
}