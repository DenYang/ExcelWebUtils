package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfig {
    private String from;
    public String getFrom(){
        return from;
    }
    public void setFrom(String from){
        this.from = from;
    }
}
