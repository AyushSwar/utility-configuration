package com.example.service;

import com.example.domain.port.VendorConfigDao;
import com.example.domain.model.VendorConfigDto;
import jakarta.inject.Singleton;

@Singleton
public class VendorConfigServiceImpl implements VendorConfigService {

    private final VendorConfigDao vendorConfigDao;

    public VendorConfigServiceImpl(VendorConfigDao vendorConfigDao) {
        this.vendorConfigDao = vendorConfigDao;
    }

    @Override
    public VendorConfigDto addVendorConfig(VendorConfigDto vendorConfig){
        return vendorConfigDao.addVendorConfig(vendorConfig);
    }
    @Override
    public VendorConfigDto getVendorConfig(String appId, String serviceType, String vendor) {
        return vendorConfigDao.getVendorConfig(appId, serviceType, vendor)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
    }
    @Override
    public boolean removeVendorConfig(String appId, String serviceType, String vendor){
        return vendorConfigDao.removeVendorConfig(appId, serviceType, vendor).isPresent();
    }
}
//This class serves as the bridge between the gRPC service endpoints and the data access layer (VendorConfigDao).
//Encapsulates the business logic related to vendor configurations. request / VendorConfigDao/ db