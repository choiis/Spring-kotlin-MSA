package org.api.appkey

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.invoke.MethodHandles

@Service
class AppKeyService {

    @Autowired
    private lateinit var appKeyClient: AppKeyClient


    fun getAppKey(appkey: String?): UserAppkey? {

        return appKeyClient.getAppKey(appkey!!);
    }

    fun postAppKey(userAppkey: UserAppkey?): UserAppkey? {

        return appKeyClient.postAppKey(userAppkey!!);
    }

    fun deleteAppKey(appkey: String?) {
        return appKeyClient.deleteAppKey(appkey!!);
    }


    companion object {
        private val log: Logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
    }
}