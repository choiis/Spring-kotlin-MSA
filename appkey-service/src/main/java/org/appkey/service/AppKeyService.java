package org.appkey.service;

import org.appkey.dao.AppkeyRepository;
import org.appkey.data.UserAppkey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppKeyService {


    @Autowired
    private AppkeyRepository repository;

    public UserAppkey findByPk(String appkey) {
        UserAppkey vo = repository.findById(appkey).get();
        return vo;
    }

    public UserAppkey save(UserAppkey vo) {
        return  repository.save(vo);
    }


    public void delete(UserAppkey vo) {
        repository.delete(vo);
    }
}
