package org.api.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.api.data.UserAppkey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {


    private final static Logger log = LoggerFactory.getLogger(ApiService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${appkey.url}")
    private String apiUrl;

    @HystrixCommand(fallbackMethod = "getFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30")
            })
    public String getHello() {
        return restTemplate.getForObject("http://localhost:8100" , String.class);
    }

    @HystrixCommand(fallbackMethod = "getFallback2",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30")
            })
    public UserAppkey getAppKey(String appkey) {
        log.info("appkey url " + apiUrl);
        ResponseEntity<UserAppkey> responseEntity = restTemplate.exchange(apiUrl + "{appkey}", HttpMethod.GET,new HttpEntity<>(new HttpHeaders()),UserAppkey.class,appkey);
        return responseEntity.getBody();
    }

    @HystrixCommand(fallbackMethod = "getFallback3",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30")
            })
    public UserAppkey postAppKey(UserAppkey userAppkey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserAppkey> entity = new HttpEntity<>(userAppkey);
        log.info("appkey url " + apiUrl);
        ResponseEntity<UserAppkey> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST,entity,UserAppkey.class);

        return responseEntity.getBody();
    }

    @HystrixCommand(fallbackMethod = "getFallback4",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "2000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30")
            })
    public void deleteAppKey(String appkey) {
        log.info("appkey url " + apiUrl);
        ResponseEntity<UserAppkey> responseEntity = restTemplate.exchange(apiUrl + "{appkey}", HttpMethod.DELETE,new HttpEntity<>(new HttpHeaders()),UserAppkey.class,appkey);
        return;
    }

    private String getFallback() {
        return "hello server unavailable";
    }

    private UserAppkey getFallback2(String appkey) {

        UserAppkey userAppkey = new UserAppkey();
        userAppkey.setAppkey(appkey + " not found");
        return userAppkey;
    }

    private UserAppkey getFallback3(UserAppkey userAppkey) {
        userAppkey.setAppkey("fail");
        return userAppkey;
    }

    private void getFallback4(String appkey) {
        return;
    }
}
