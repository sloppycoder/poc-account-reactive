package org.vino9.poc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.JavaResteasyServerCodegen",
    date = "2021-06-05T14:09:03.284Z[GMT]")
public class AccountDetail {
  private String accountNo = null;
  private String country = null;
  private String branchCode = null;
  private String currency = null;
  private List<Balance> balance = new ArrayList<Balance>();

  /** */
  @Schema(example = "10001001", required = true, description = "")
  @JsonProperty("accountNo")
  @NotNull
  public String getAccountNo() {
    return accountNo;
  }

  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  /** */
  @Schema(example = "Singapore", required = true, description = "")
  @JsonProperty("country")
  @NotNull
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  /** */
  @Schema(example = "2002", required = true, description = "")
  @JsonProperty("branchCode")
  @NotNull
  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  /** */
  @Schema(example = "SGD", required = true, description = "")
  @JsonProperty("currency")
  @NotNull
  @Size(min = 3, max = 3)
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /** */
  @Schema(required = true, description = "")
  @JsonProperty("balance")
  @NotNull
  public List<Balance> getBalance() {
    return balance;
  }

  public void setBalance(List<Balance> balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountDetail accountDetail = (AccountDetail) o;
    return Objects.equals(accountNo, accountDetail.accountNo)
        && Objects.equals(country, accountDetail.country)
        && Objects.equals(branchCode, accountDetail.branchCode)
        && Objects.equals(currency, accountDetail.currency)
        && Objects.equals(balance, accountDetail.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountNo, country, branchCode, currency, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountDetail {\n");

    sb.append("    accountNo: ").append(toIndentedString(accountNo)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    branchCode: ").append(toIndentedString(branchCode)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
