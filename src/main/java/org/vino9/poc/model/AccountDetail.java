package org.vino9.poc.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.vino9.poc.model.Balance;
import java.io.Serializable;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen")
public class AccountDetail  implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private String accountNo;
  private String currency;
  private String country;
  private String branchCode;
  private List<Balance> balance = new ArrayList<>();

  /**
   **/
  
  @ApiModelProperty(example = "10001001", required = true, value = "")
  @JsonProperty("accountNo")
  @NotNull
  public String getAccountNo() {
    return accountNo;
  }
  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }

  /**
   **/
  
  @ApiModelProperty(example = "SGD", required = true, value = "")
  @JsonProperty("currency")
  @NotNull
 @Size(min=3,max=3)  public String getCurrency() {
    return currency;
  }
  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   **/
  
  @ApiModelProperty(example = "SG", required = true, value = "")
  @JsonProperty("country")
  @NotNull
 @Size(min=2,max=2)  public String getCountry() {
    return country;
  }
  public void setCountry(String country) {
    this.country = country;
  }

  /**
   **/
  
  @ApiModelProperty(example = "2002", required = true, value = "")
  @JsonProperty("branchCode")
  @NotNull
  public String getBranchCode() {
    return branchCode;
  }
  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  /**
   **/
  
  @ApiModelProperty(required = true, value = "")
  @JsonProperty("balance")
  @NotNull
  public List<Balance> getBalance() {
    return balance;
  }
  public void setBalance(List<Balance> balance) {
    this.balance = balance;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountDetail accountDetail = (AccountDetail) o;
    return Objects.equals(accountNo, accountDetail.accountNo) &&
        Objects.equals(currency, accountDetail.currency) &&
        Objects.equals(country, accountDetail.country) &&
        Objects.equals(branchCode, accountDetail.branchCode) &&
        Objects.equals(balance, accountDetail.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountNo, currency, country, branchCode, balance);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountDetail {\n");
    
    sb.append("    accountNo: ").append(toIndentedString(accountNo)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    branchCode: ").append(toIndentedString(branchCode)).append("\n");
    sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

