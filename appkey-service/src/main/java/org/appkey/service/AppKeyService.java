package org.appkey.service;

import org.appkey.dao.AppkeyRepository;
import org.appkey.data.Sm01vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppKeyService {


    @Autowired
    private AppkeyRepository repository;

    public Sm01vo findByPk(String appkey) {
        Sm01vo vo = repository.findById(appkey).get();
        return vo;
    }

    public Sm01vo save(Sm01vo vo) {
        return  repository.save(vo);
    }


    public void delete(Sm01vo vo) {
        repository.delete(vo);
    }
}
