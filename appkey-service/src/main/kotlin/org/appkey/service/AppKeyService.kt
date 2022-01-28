package org.appkey.service

import org.springframework.beans.factory.annotation.Autowired
import org.appkey.dao.AppkeyRepository
import org.appkey.data.UserAppkey
import org.springframework.stereotype.Service

@Service
open class AppKeyService {

    @Autowired
    private lateinit var repository: AppkeyRepository

    open fun findByPk(appkey: String): UserAppkey {
        return repository.findById(appkey).get()
    }

    open fun save(vo: UserAppkey?): UserAppkey? {
        return repository.save(vo!!)
    }

    open fun delete(vo: UserAppkey) {
        repository.delete(vo)
    }
}