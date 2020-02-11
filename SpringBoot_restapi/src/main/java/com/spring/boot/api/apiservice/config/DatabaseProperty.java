package com.spring.boot.api.apiservice.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * yml에 설정된 db 설정파일을 가져올 class
 * @author DraChacha
 *
 */
@Getter
@Setter
@Component
@ConfigurationProperties("spring.datasource")
public class DatabaseProperty {
    private String url;
    // slave가 복수 일 경우를 위한 list설정
    private List<Slave> slaveList;

    private String username;
    private String password;

    @Getter
    @Setter
    public static class Slave {
        private String name;
        private String url;
    }
}
