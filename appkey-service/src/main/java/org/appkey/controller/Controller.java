package org.appkey.controller;


import org.appkey.data.UserAppkey;
import org.appkey.service.AppKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class Controller {

    private final static Logger log = LoggerFactory.getLogger(Controller.class);

    @ResponseBody
    @RequestMapping(value = "/sm01", method = RequestMethod.GET)
    public ResponseEntity<String> selecttest() {
        log.info("call key ");
        return new ResponseEntity<String>("call", HttpStatus.OK);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<UserAppkey> noSuchElementException(NoSuchElementException ex) {
        log.error(ex.getLocalizedMessage());
        return new ResponseEntity<UserAppkey>(new UserAppkey(),HttpStatus.NOT_FOUND);
    }

    @Autowired
    private AppKeyService appKeyService;

    @ResponseBody
    @RequestMapping(value = "/sm01/{appkey}", method = RequestMethod.GET)
    public ResponseEntity<UserAppkey> findByAppkey(@PathVariable String appkey) {
        log.info("findByAppkey ", appkey);
        UserAppkey vo = appKeyService.findByPk(appkey);
        return new ResponseEntity<UserAppkey>(vo, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/sm01", method = RequestMethod.POST)
    public ResponseEntity<UserAppkey> saveAppKey(@RequestBody UserAppkey vo) {
        log.info("saveAppKey ", vo);
        vo = appKeyService.save(vo);
        return new ResponseEntity<UserAppkey>(vo, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/sm01/{appkey}", method = RequestMethod.DELETE)
    public ResponseEntity<UserAppkey> deleteAppKey(@PathVariable String appkey) {
        UserAppkey vo = new UserAppkey();
        vo.setAppkey(appkey);
        log.info("saveAppKey ", vo);
        appKeyService.delete(vo);
        return new ResponseEntity<UserAppkey>(vo, HttpStatus.NO_CONTENT);
    }
}
