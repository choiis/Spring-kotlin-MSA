package org.appkey.application.user

import org.appkey.application.user.dto.UserAppkeyResponse
import org.springframework.beans.factory.annotation.Autowired
import org.appkey.domain.user.UserAppkeyRepository
import org.appkey.domain.user.UserAppkey
import org.springframework.stereotype.Service

@Service
class AppKeyService {

    @Autowired
    private lateinit var repositoryUser: UserAppkeyRepository

    fun findUserAppKeyByPk(appkey: String): UserAppkeyResponse {
        return AppKeyAssembler.entityToResponse(repositoryUser.findById(appkey).get())
    }

    fun saveUserAppKey(vo: UserAppkey): UserAppkeyResponse? {
        return AppKeyAssembler.entityToResponse(repositoryUser.save(vo))
    }

    fun deleteUserAppkey(vo: UserAppkey) {
        repositoryUser.delete(vo)
    }
}