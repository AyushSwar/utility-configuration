package com.example.domain.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record VendorConfigDto (String appId,
        String serviceType,
        String vendor,
        boolean active,
        String cashbackAccount,
        long cashbackAmount,
        String cashbackImage,
        double cashbackPercent,
        long commissionAmount,
        String commissionIncomeAccount,
        double commissionPercent,
        String commissionReceivableAccount,
        String description,
        String displayImage,
        String feeCollectionAccount,
        String feeImage,
        String idempotentId,
        String serviceId,
        long fee,
        long feeAmount,
        long maxCashback,
        long maxTransactionAmount,
        String prefundingAccount,
        String serviceGroup) implements Serializable{}
