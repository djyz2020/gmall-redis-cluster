package com.fwd.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GmallRedisClusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallRedisClusterApplication.class, args);
    }

}
