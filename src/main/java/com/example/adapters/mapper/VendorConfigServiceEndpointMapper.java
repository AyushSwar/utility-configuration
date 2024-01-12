package com.example.adapters.mapper;

import com.example.domain.model.VendorConfigDto;
import com.example.proto.VendorConfig;

public class VendorConfigServiceEndpointMapper {

    private VendorConfigServiceEndpointMapper(){}
    public static VendorConfig mapDtoToProto(VendorConfigDto dto) {
        return VendorConfig.newBuilder()
                .setAppId(dto.appId())
                .setServiceType(dto.serviceType())
                .setVendor(dto.vendor())
                .setActive(dto.active())
                .setCashbackAccount(dto.cashbackAccount())
                .setCashbackAmount(dto.cashbackAmount())
                .setCashbackImage(dto.cashbackImage())
                .setCashbackPercent(dto.cashbackPercent())
                .setCommissionAmount(dto.commissionAmount())
                .setCommissionIncomeAccount(dto.commissionIncomeAccount())
                .setCommissionPercent(dto.commissionPercent())
                .setCommissionReceivableAccount(dto.commissionReceivableAccount())
                .setDescription(dto.description())
                .setDisplayImage(dto.displayImage())
                .setFeeCollectionAccount(dto.feeCollectionAccount())
                .setFeeImage(dto.feeImage())
                .setIdempotentId(dto.idempotentId())
                .setServiceId(dto.serviceId())
                .setFee(dto.fee())
                .setFeeAmount(dto.feeAmount())
                .setMaxCashback(dto.maxCashback())
                .setMaxTransactionAmount(dto.maxTransactionAmount())
                .setPrefundingAccount(dto.prefundingAccount())
                .setServiceGroup(dto.serviceGroup())
                .build();
    }
    public static VendorConfigDto mapProtoToDto(VendorConfig proto) {
        return VendorConfigDto.builder()
                // map proto to dto
                .appId(proto.getAppId())
                .serviceType(proto.getServiceType())
                .vendor(proto.getVendor())
                .active(proto.getActive())
                .cashbackAccount(proto.getCashbackAccount())
                .cashbackAmount(proto.getCashbackAmount())
                .cashbackImage(proto.getCashbackImage())
                .cashbackPercent(proto.getCashbackPercent())
                .commissionAmount(proto.getCommissionAmount())
                .commissionIncomeAccount(proto.getCommissionIncomeAccount())
                .commissionPercent(proto.getCommissionPercent())
                .commissionReceivableAccount(proto.getCommissionReceivableAccount())
                .description(proto.getDescription())
                .displayImage(proto.getDisplayImage())
                .feeCollectionAccount(proto.getFeeCollectionAccount())
                .feeImage(proto.getFeeImage())
                .idempotentId(proto.getIdempotentId())
                .serviceId(proto.getServiceId())
                .fee(proto.getFee())
                .feeAmount(proto.getFeeAmount())
                .maxCashback(proto.getMaxCashback())
                .maxTransactionAmount(proto.getMaxTransactionAmount())
                .prefundingAccount(proto.getPrefundingAccount())
                .serviceGroup(proto.getServiceGroup())
                .build();
    }

}
