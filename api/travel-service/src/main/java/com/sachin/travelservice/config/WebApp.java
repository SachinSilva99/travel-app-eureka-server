package com.sachin.travelservice.config;

import com.sachin.travelservice.filters.CustomerJwtAuthenticationFilter;
import com.sachin.travelservice.filters.JwtAuthenticationFilter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebApp {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> travelAdminFilter() {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtAuthenticationFilter());
        registrationBean.addUrlPatterns("/api/v1/travels/*");
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean<CustomerJwtAuthenticationFilter> customerFilter() {
        FilterRegistrationBean<CustomerJwtAuthenticationFilter> registrationBean
                = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomerJwtAuthenticationFilter());
        registrationBean.addUrlPatterns("/api/v1/gettravels/**");
        return registrationBean;
    }
    @Bean
    public WebClient webClient() {
        final int size = 20 * 1024 * 1024;
        final ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
        return WebClient.builder()
                .exchangeStrategies(strategies)
                .build();
    }


}
