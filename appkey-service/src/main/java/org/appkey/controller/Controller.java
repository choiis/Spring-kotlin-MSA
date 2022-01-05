package org.appkey.controller;


import org.appkey.data.Sm01vo;
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
    public ResponseEntity<Sm01vo> noSuchElementException(NoSuchElementException ex) {
        log.error(ex.getLocalizedMessage());
        return new ResponseEntity<Sm01vo>(new Sm01vo(),HttpStatus.NOT_FOUND);
    }

    @Autowired
    private AppKeyService appKeyService;

    @ResponseBody
    @RequestMapping(value = "/sm01/{appkey}", method = RequestMethod.GET)
    public ResponseEntity<Sm01vo> findByAppkey(@PathVariable String appkey) {
        log.info("findByAppkey ", appkey);
        Sm01vo vo = appKeyService.findByPk(appkey);
        return new ResponseEntity<Sm01vo>(vo, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/sm01", method = RequestMethod.POST)
    public ResponseEntity<Sm01vo> saveAppKey(@RequestBody Sm01vo vo) {
        log.info("saveAppKey ", vo);
        vo = appKeyService.save(vo);
        return new ResponseEntity<Sm01vo>(vo, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/sm01/{appkey}", method = RequestMethod.DELETE)
    public ResponseEntity<Sm01vo> deleteAppKey(@PathVariable String appkey) {
        Sm01vo vo = new Sm01vo();
        vo.setAppkey(appkey);
        log.info("saveAppKey ", vo);
        appKeyService.delete(vo);
        return new ResponseEntity<Sm01vo>(vo, HttpStatus.NO_CONTENT);
    }
}
