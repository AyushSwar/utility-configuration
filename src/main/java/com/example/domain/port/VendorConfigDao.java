package com.example.domain.port;

import com.example.domain.model.VendorConfigDto;

import java.util.Optional;

public interface VendorConfigDao {
    VendorConfigDto addVendorConfig(VendorConfigDto vendorConfig);
    Optional<VendorConfigDto> getVendorConfig(String appId, String serviceType, String vendor);
    Optional<VendorConfigDto> removeVendorConfig(String appId, String serviceTypZ, String vendor);
}
//Defines methods for interacting with the data store, in this case, adding, getting, and removing vendor configurations.