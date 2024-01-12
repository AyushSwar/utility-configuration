package com.example.adapters.factory;

import com.example.api.interceptor.ValidationInterceptor;
import com.example.common.AddVendorConfigRequestValidator;
import io.grpc.ServerInterceptor;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class InterceptorFactory {

    @Singleton
    @Bean
    public ServerInterceptor validationInterceptor(AddVendorConfigRequestValidator validator) {
        return new ValidationInterceptor(validator);
    }
}
