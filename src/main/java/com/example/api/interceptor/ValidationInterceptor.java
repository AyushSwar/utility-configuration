package com.example.api.interceptor;

import com.example.common.AddVendorConfigRequestValidator;
import com.example.domain.model.VendorConfigDto;
import io.grpc.*;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

@Singleton
public class ValidationInterceptor implements ServerInterceptor {

    private final AddVendorConfigRequestValidator validator;
    private static final Logger logger = LoggerFactory.getLogger(ValidationInterceptor.class);
    private static final AtomicBoolean initialized = new AtomicBoolean(false);

    @Inject
    public ValidationInterceptor(AddVendorConfigRequestValidator validator) {
        this.validator = validator;
        if (initialized.compareAndSet(false, true)) {
            logger.info("ValidationInterceptor Instantiated Successfully.");
        }
    }

    @Override
    public <RequestType, RespT> ServerCall.Listener<RequestType> interceptCall(
            ServerCall<RequestType, RespT> call, Metadata headers, ServerCallHandler<RequestType, RespT> next) {

        logger.info("Intercepting call........");

        ServerCall.Listener<RequestType> delegate = next.startCall(call, headers);

        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(delegate) {

            @Override
            public void onMessage(RequestType message) {
                if (message instanceof VendorConfigDto vendorconfigdto) {
                    validateAndContinue(vendorconfigdto);
                } else {
                    delegate().onMessage(message);
                }
            }

            private void validateAndContinue(VendorConfigDto message) {
                try {
                    validator.validate(message);
                    delegate().onMessage((RequestType) message);
                } catch (IllegalArgumentException e) {
                    logger.error("Interceptor Validation Failed: {}", e.getMessage());
                    call.close(Status.INVALID_ARGUMENT.withDescription(e.getMessage()), new Metadata());
                }
            }
        };
    }
}
