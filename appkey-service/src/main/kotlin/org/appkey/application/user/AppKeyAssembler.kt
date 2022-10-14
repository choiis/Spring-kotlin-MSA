package org.appkey.application.user

import org.appkey.application.user.dto.UserAppkeyResponse
import org.appkey.domain.user.UserAppkey

class AppKeyAssembler {

    companion object {

        fun entityToResponse(entity: UserAppkey) : UserAppkeyResponse {
            return UserAppkeyResponse(entity.appkey,entity.userid,entity.usertype);
        }
    }
}