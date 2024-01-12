package com.example.adapters.daoscyllaimpl;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.Row;

import com.example.domain.model.VendorConfigDto;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;


public sealed class VendorConfigStatement permits VendorConfigDaoImpl {

    private static final String VENDOR_CONFIG_TABLE = "utility.vendor_config";
    private static final String APP_ID = "app_id";
    private static final String SERVICE_TYPE = "service_type";
    private static final String VENDOR = "vendor";
    private static final String ACTIVE = "active";
    private static final String CASHBACK_ACCOUNT = "cashback_account";
    private static final String CASHBACK_AMOUNT = "cashback_amount";
    private static final String CASHBACK_IMAGE = "cashback_image";
    private static final String CASHBACK_PERCENT = "cashback_percent";
    private static final String COMMISSION_AMOUNT = "commission_amount";
    private static final String COMMISSION_INCOME_ACCOUNT = "commission_income_account";
    private static final String COMMISSION_PERCENT = "commission_percent";
    private static final String COMMISSION_RECEIVABLE_ACCOUNT = "commission_receivable_account";
    private static final String DESCRIPTION = "description";
    private static final String DISPLAY_IMAGE = "display_image";
    private static final String FEE_COLLECTION_ACCOUNT = "fee_collection_account";
    private static final String FEE_IMAGE = "fee_image";
    private static final String MAX_CASHBACK = "max_cashback";
    private static final String SERVICE_GROUP = "service_group";
    private static final String MAX_TRANSACTION_AMOUNT = "max_transaction_amount";
    private static final String PREFUNDING_ACCOUNT = "prefunding_account";
    private static final String FEE = "fee";
    private static final String FEE_AMOUNT = "fee_amount";
    private static final String IDEMPOTENT_ID = "idempotent_id";
    private static final String SERVICE_ID = "service_id";
    private static final String SELECT_FROM = "SELECT * FROM ";
    private static final String WHERE = " WHERE ";
    private static final String AND = " AND ";
    private static final String DELETE_FROM = "DELETE FROM ";
    private static final String INSERT_INTO = "INSERT INTO ";

    private static final String SAVE_BY_TYPE_QUERY = INSERT_INTO + VENDOR_CONFIG_TABLE +
            " ("
            + APP_ID + ", "
            + SERVICE_TYPE + ", "
            + VENDOR + ", "
            + ACTIVE + ", "
            + CASHBACK_ACCOUNT + ", "
            + CASHBACK_AMOUNT + ", "
            + CASHBACK_IMAGE + ", "
            + CASHBACK_PERCENT + ", "
            + COMMISSION_AMOUNT + ", "
            + COMMISSION_INCOME_ACCOUNT + ", "
            + COMMISSION_PERCENT + ", "
            + COMMISSION_RECEIVABLE_ACCOUNT + ", "
            + DESCRIPTION + ", "
            + DISPLAY_IMAGE + ", "
            + FEE_COLLECTION_ACCOUNT + ", "
            + FEE_IMAGE + ", "
            + FEE + ", "
            + FEE_AMOUNT + ", "
            + IDEMPOTENT_ID + ", "
            + SERVICE_ID + ", "
            + MAX_CASHBACK + ", "
            + MAX_TRANSACTION_AMOUNT + ", "
            + PREFUNDING_ACCOUNT + ", "
            + SERVICE_GROUP + ") " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_BY_TYPE_QUERY = DELETE_FROM + VENDOR_CONFIG_TABLE
            + WHERE
            + APP_ID
            + " = ?" + AND
            + SERVICE_TYPE
            + " = ?" + AND
            + VENDOR + " = ?";

    private static final String GET_BY_TYPE_QUERY = SELECT_FROM  + VENDOR_CONFIG_TABLE
            + WHERE
            + APP_ID
            + " = ?" + AND
            + SERVICE_TYPE
            + " = ?" + AND
            + VENDOR + " = ?";
    protected final CqlSession cqlSession;
    private final PreparedStatement addVendorConfigStatement;
    private final PreparedStatement getVendorConfigStatement;
    private final PreparedStatement removeVendorConfigStatement;

    public VendorConfigStatement(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
        addVendorConfigStatement = cqlSession.prepare(SAVE_BY_TYPE_QUERY);
        getVendorConfigStatement = cqlSession.prepare(GET_BY_TYPE_QUERY);
        removeVendorConfigStatement = cqlSession.prepare(DELETE_BY_TYPE_QUERY);
    }

//     add, remove, get VendorConfigDto object from a database.
    protected BoundStatement addVendorConfigStatement(VendorConfigDto dto) {
        return addVendorConfigStatement.bind()
                .setString(APP_ID, dto.appId())
                .setString(SERVICE_TYPE, dto.serviceType())
                .setString(VENDOR, dto.vendor())
                .setBoolean(ACTIVE, dto.active())
                .setString(CASHBACK_ACCOUNT, dto.cashbackAccount())
                .setLong(CASHBACK_AMOUNT, dto.cashbackAmount())
                .setString(CASHBACK_IMAGE, dto.cashbackImage())
                .setDouble(CASHBACK_PERCENT, dto.cashbackPercent())
                .setLong(COMMISSION_AMOUNT, dto.commissionAmount())
                .setString(COMMISSION_INCOME_ACCOUNT, dto.commissionIncomeAccount())
                .setDouble(COMMISSION_PERCENT, dto.cashbackPercent()) // Assuming this is not a mistake
                .setString(COMMISSION_RECEIVABLE_ACCOUNT, dto.commissionReceivableAccount())
                .setString(DESCRIPTION, dto.description())
                .setString(DISPLAY_IMAGE, dto.displayImage())
                .setString(FEE_COLLECTION_ACCOUNT, dto.feeCollectionAccount())
                .setString(FEE_IMAGE, dto.feeImage())
                .setString(IDEMPOTENT_ID, dto.idempotentId())
                .setString(SERVICE_ID, dto.serviceId())
                .setLong(FEE, dto.fee())
                .setLong(FEE_AMOUNT, dto.feeAmount())
                .setLong(MAX_CASHBACK, dto.maxCashback())
                .setLong(MAX_TRANSACTION_AMOUNT, dto.maxTransactionAmount())
                .setString(PREFUNDING_ACCOUNT, dto.prefundingAccount())
                .setString(SERVICE_GROUP, dto.serviceGroup());
    }
    protected BoundStatement getVendorConfigStatement(String appId, String serviceType, String vendor) {
        return getVendorConfigStatement.bind()
                .setString(APP_ID, appId)
                .setString(SERVICE_TYPE, serviceType)
                .setString(VENDOR, vendor);
    }

    protected BoundStatement removeVendorConfigStatement(String appId, String serviceType, String vendor) {
        return removeVendorConfigStatement.bind()
                .setString(APP_ID, appId)
                .setString(SERVICE_TYPE, serviceType)
                .setString(VENDOR, vendor);
    }

//    mapRowToVendorConfigDto method transforms a database row (Row object) into a VendorConfigDto
//     This is commonly used to convert database query results into application-specific data structures.
    protected final VendorConfigDto mapRowToVendorConfigDto(Row row) {
        return VendorConfigDto.builder()
                .appId(row.getString(APP_ID))
                .serviceType(row.getString(SERVICE_TYPE))
                .vendor(row.getString(VENDOR))
                .active(row.getBoolean(ACTIVE))
                .cashbackAccount(row.getString(CASHBACK_ACCOUNT))
                .cashbackAmount(row.getLong(CASHBACK_AMOUNT))
                .cashbackImage(row.getString(CASHBACK_IMAGE))
                .cashbackPercent(row.getDouble(CASHBACK_PERCENT))
                .commissionAmount(row.getLong(COMMISSION_AMOUNT))
                .commissionIncomeAccount(row.getString(COMMISSION_INCOME_ACCOUNT))
                .commissionPercent(row.getDouble(COMMISSION_PERCENT))
                .commissionReceivableAccount(row.getString(COMMISSION_RECEIVABLE_ACCOUNT))
                .description(row.getString(DESCRIPTION))
                .displayImage(row.getString(DISPLAY_IMAGE))
                .feeCollectionAccount(row.getString(FEE_COLLECTION_ACCOUNT))
                .feeImage(row.getString(FEE_IMAGE))
                .idempotentId(row.getString(IDEMPOTENT_ID))
                .serviceId(row.getString(SERVICE_ID))
                .fee(row.getLong(FEE))
                .feeAmount(row.getLong(FEE_AMOUNT))
                .maxCashback(row.getLong(MAX_CASHBACK))
                .maxTransactionAmount(row.getLong(MAX_TRANSACTION_AMOUNT))
                .prefundingAccount(row.getString(PREFUNDING_ACCOUNT))
                .serviceGroup(row.getString(SERVICE_GROUP))
                .build();
    }


}
