package com.example.common;

import com.example.domain.model.VendorConfigDto;
import jakarta.inject.Named;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
@Named("AddVendorConfigValidator")
public class AddVendorConfigRequestValidator implements Validator<VendorConfigDto> {

    private static final Logger logger = LoggerFactory.getLogger(AddVendorConfigRequestValidator.class);

    @Override
    public void validate(VendorConfigDto request) {
        List<String> errors = new ArrayList<>();

        validateNotBlank(errors, "App ID", request.appId());
        validateNotBlank(errors, "Service Type", request.serviceType());
        validateNotBlank(errors, "Vendor", request.vendor());
        validateNotBlank(errors, "Cashback Account", request.cashbackAccount());
        validateNotNull(errors, "Cashback Amount", request.cashbackAmount());
        validateNotBlank(errors, "Cashback Image", request.cashbackImage());
        validateNotNull(errors, "Cashback Percent", request.cashbackPercent());
        validateNotNull(errors, "Commission Amount", request.commissionAmount());
        validateNotBlank(errors, "Commission Income Account", request.commissionIncomeAccount());
        validateNotNull(errors, "Commission Percent", request.commissionPercent());
        validateNotBlank(errors, "Commission Receivable Account", request.commissionReceivableAccount());
        validateNotBlank(errors, "Description", request.description());
        validateNotBlank(errors, "Display Image", request.displayImage());
        validateNotBlank(errors, "Fee Collection Account", request.feeCollectionAccount());
        validateNotBlank(errors, "Fee Image", request.feeImage());
        validateNotBlank(errors, "Idempotent ID", request.idempotentId());
        validateNotNull(errors, "Fee", request.fee());
        validateNotNull(errors, "Fee Amount", request.feeAmount());
        validateNotNull(errors, "Max Cashback", request.maxCashback());
        validateNotNull(errors, "Max Transaction Amount", request.maxTransactionAmount());
        validateNotBlank(errors, "Prefunding Account", request.prefundingAccount());
        validateNotBlank(errors, "Service ID", request.serviceId());
        validateNotBlank(errors, "Service Group", request.serviceGroup());

        if (!errors.isEmpty()) {
            String errorMessage = "Missing or invalid fields:\n" + errors.stream().collect(Collectors.joining("\n"));
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateNotBlank(List<String> errors, String fieldName, String value) {
        if (value.isBlank()) {
            errors.add(fieldName + " cannot be blank.");
        }
    }

    private void validateNotNull(List<String> errors, String fieldName, Object value) {
        if (value == null || (value instanceof String && ((String) value).isBlank())
                || (value instanceof Number && ((Number) value).doubleValue() <= 0)) {
            errors.add(fieldName + " is invalid.");
        }
    }
}
