package org.vino9.poc.mapper;

import io.vertx.mutiny.sqlclient.Row;
import org.vino9.poc.model.AccountDetail;

public class RowToModelMapper {
    public static AccountDetail rowToAccountDetail(Row row) {
        var account = new AccountDetail();
        account.setAccountNo(row.getString(0));
        account.setCurrency(row.getString(1));
        account.setCountry(row.getString(2));
        account.setBranchCode(row.getString(3));
        return account;
    }

    private RowToModelMapper(){}
}
