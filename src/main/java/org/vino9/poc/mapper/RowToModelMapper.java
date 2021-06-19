package org.vino9.poc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.vino9.poc.model.AccountDetail;

public class RowToModelMapper {

    private RowToModelMapper() {
    }

    public static AccountDetail rowToAccountDetail(ResultSet rs) throws SQLException {
        var account = new AccountDetail();
        account.setAccountNo(rs.getString(1));
        account.setCurrency(rs.getString(2));
        account.setCountry(rs.getString(3));
        account.setBranchCode(rs.getString(4));
        return account;
    }
}
