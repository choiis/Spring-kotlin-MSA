package org.api;

import org.api.data.UserAppkey;
import org.api.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@EnableHystrix
public class ApiServiceApplication {

    private final static Logger log = LoggerFactory.getLogger(ApiServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ApiServiceApplication.class, args);
    }

    @Autowired
    private ApiService apiService;

    @GetMapping(value = {"/hello"})
    public String hello() {
        return apiService.getHello();
    }


    @GetMapping(value = {"/appkey/{appkey}"})
    public ResponseEntity<UserAppkey> getAppKey(@PathVariable String appkey) {
        log.info("getAppKey ", appkey);
        return new ResponseEntity<UserAppkey>(apiService.getAppKey(appkey), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/appkey", method = RequestMethod.POST)
    public ResponseEntity<UserAppkey> saveAppKey(@RequestBody UserAppkey vo) {
        log.info("saveAppKey ", vo);
        vo = apiService.postAppKey(vo);
        return new ResponseEntity<UserAppkey>(vo, HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/appkey/{appkey}", method = RequestMethod.DELETE)
    public ResponseEntity<UserAppkey> deleteAppKey(@PathVariable String appkey) {
        log.info("deleteAppKey ");
        apiService.deleteAppKey(appkey);
        return new ResponseEntity<UserAppkey>(HttpStatus.NO_CONTENT);
    }

}
