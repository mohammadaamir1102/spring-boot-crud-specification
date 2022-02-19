package com.spec.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

    @Bean
    public Mapper dozerMapper(){
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }
}
