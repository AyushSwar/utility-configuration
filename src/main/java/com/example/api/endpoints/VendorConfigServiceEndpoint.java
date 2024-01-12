package com.example.api.endpoints;

import com.example.common.Validator;
import com.example.domain.model.VendorConfigDto;
import com.example.adapters.mapper.VendorConfigServiceEndpointMapper;
import com.example.proto.*;
import com.example.service.VendorConfigService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import jakarta.inject.Named;
import jakarta.inject.Singleton;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.adapters.mapper.VendorConfigServiceEndpointMapper.mapDtoToProto;

@Singleton
public class VendorConfigServiceEndpoint extends VendorConfigServiceGrpc.VendorConfigServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(VendorConfigServiceEndpoint.class);


    private final VendorConfigService vendorConfigService;
    private final Validator<VendorConfigDto> vendorConfigValidator;

    public VendorConfigServiceEndpoint(VendorConfigService vendorConfigService,
                                       @Named("AddVendorConfigValidator") Validator<VendorConfigDto> vendorConfigValidator) {
        this.vendorConfigService = vendorConfigService;
        this.vendorConfigValidator = vendorConfigValidator;
    }

    @Override
    public void addVendorConfig(VendorConfig request, StreamObserver<VendorConfig> responseObserver) {
        try {
            VendorConfigDto dto = VendorConfigServiceEndpointMapper.mapProtoToDto(request);
            vendorConfigValidator.validate(dto);
            VendorConfigDto savedConfig = vendorConfigService.addVendorConfig(dto);
            VendorConfig response = VendorConfigServiceEndpointMapper.mapDtoToProto(savedConfig);
            responseObserver.onNext(response);
            logger.info("Vendor config add successfully.");


        } catch (IllegalArgumentException e) {
            // Send an error response to the client
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription(e.getMessage()).asRuntimeException());
        } catch (Exception e) {
            // Log other unexpected errors
            logger.error("Error saving vendor config", e);
            // Send an internal server error response to the client
            responseObserver.onError(Status.INTERNAL.withDescription("Error saving vendor config").asRuntimeException());
        } finally {
            responseObserver.onCompleted();
        }
    }


    @Override
    public void getVendorConfig(GetVendorConfigRequest request, StreamObserver<VendorConfig> responseObserver) {
        try {
            VendorConfigDto dto = vendorConfigService.getVendorConfig(request.getAppId(), request.getServiceType(), request.getVendor());
            VendorConfig response = mapDtoToProto(dto);
            responseObserver.onNext(response);
            logger.info("Vendor config get successfully.");
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Error fetching vendor config").asRuntimeException());
        } finally {
            responseObserver.onCompleted();
        }
    }

    @Override
    public void removeVendorConfig(RemoveVendorConfigRequest request, StreamObserver<RemoveVendorConfigResponse> responseObserver) {
        try {
            boolean success = vendorConfigService.removeVendorConfig(request.getAppId(), request.getServiceType(), request.getVendor());
            String message = success ? "Vendor_config row deleted successfully." : "Vendor_config row not found for deletion.";

            RemoveVendorConfigResponse response = RemoveVendorConfigResponse.newBuilder()
                    .setSuccess(success)
                    .setMessage(message)
                    .build();

            responseObserver.onNext(response);
            logger.info("Vendor config remove successfully.");
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription("Error deleting vendor config").asRuntimeException());
        } finally {
            responseObserver.onCompleted();
        }
    }


}