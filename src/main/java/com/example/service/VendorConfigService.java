package com.example.service;

import com.example.domain.model.VendorConfigDto;

public interface VendorConfigService {
    VendorConfigDto addVendorConfig(VendorConfigDto vendorConfig);
    VendorConfigDto getVendorConfig(String appId, String serviceType, String vendor);
    boolean removeVendorConfig(String appId, String serviceType, String vendor);
}


//Declares methods that define the business logic related to vendor configurations.