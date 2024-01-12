package com.example.adapters.daoscyllaimpl;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.example.domain.port.VendorConfigDao;
import com.example.exceptions.DaoException;
import com.example.domain.model.VendorConfigDto;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Singleton
public final class VendorConfigDaoImpl extends VendorConfigStatement implements VendorConfigDao {
        @Inject
        VendorConfigDaoImpl(CqlSession cqlSession) {
            super(cqlSession);
        }

        @Override
        public VendorConfigDto addVendorConfig(VendorConfigDto vendorConfigDto) {
            log.debug("Saving vendor config :: vendorConfig : {}", vendorConfigDto);
            try {
                BoundStatement statement = addVendorConfigStatement(vendorConfigDto);
                cqlSession.execute(statement);
                log.debug("Vendor config saved successfully");

                // Retrieve the vendor config after saving
                BoundStatement getStatement = getVendorConfigStatement(
                        vendorConfigDto.appId(), vendorConfigDto.serviceType(), vendorConfigDto.vendor()
                );
                ResultSet getResult = cqlSession.execute(getStatement);
                Row row = getResult.one();
                return row != null ? mapRowToVendorConfigDto(row) : null;
            } catch (Exception e) {
                log.error("Error saving vendor config", e);
                throw new DaoException.AddException("Error saving vendor config", e);
            }
        }

        @Override
        public Optional<VendorConfigDto> getVendorConfig(String appId, String serviceType, String vendor) {
            log.debug("getVendorConfig :: appId: {} , serviceType :{}, vendor : {}", appId, serviceType, vendor);
            try {
                BoundStatement statement = getVendorConfigStatement(appId, serviceType, vendor);
                ResultSet resultSet = cqlSession.execute(statement);
                Row row = resultSet.one();
                return row != null ? Optional.ofNullable(mapRowToVendorConfigDto(row)) : Optional.empty();
            } catch (Exception e) {
                log.error("Unable to fetch vendor config.", e);
                throw new DaoException.GetException("Unable to fetch vendor config.", e);
            }
        }

        @Override
        public Optional<VendorConfigDto> removeVendorConfig(String appId, String serviceType, String vendor) {
            log.debug("Deleting vendor config :: appId : {}, serviceType : {}, vendor : {}", appId, serviceType, vendor);
            try {
                // Retrieve the vendor config before deletion
                Optional<VendorConfigDto> removeConfigOptional = getVendorConfig(appId, serviceType, vendor);

                // Execute the delete statement
                BoundStatement statement = removeVendorConfigStatement(appId, serviceType, vendor);
                cqlSession.execute(statement);

                // Return the deleted VendorConfigDto if present
                return removeConfigOptional;
            } catch (Exception e) {
                log.error("Error deleting vendor config", e);
                throw new DaoException.RemoveException("Error deleting vendor config", e);
            }
        }
    }
