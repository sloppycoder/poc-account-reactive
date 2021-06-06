package org.vino9.poc.model;

import java.util.Objects;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.io.Serializable;
import javax.validation.constraints.*;
import io.swagger.annotations.*;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaResteasyServerCodegen")
public class Balance  implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private BigDecimal available;
  private BigDecimal ledger;
  private LocalDate balanceDate;

  /**
   **/
  
  @ApiModelProperty(example = "100.01", required = true, value = "")
  @JsonProperty("available")
  @NotNull
  public BigDecimal getAvailable() {
    return available;
  }
  public void setAvailable(BigDecimal available) {
    this.available = available;
  }

  /**
   **/
  
  @ApiModelProperty(example = "100.02", required = true, value = "")
  @JsonProperty("ledger")
  @NotNull
  public BigDecimal getLedger() {
    return ledger;
  }
  public void setLedger(BigDecimal ledger) {
    this.ledger = ledger;
  }

  /**
   **/
  
  @ApiModelProperty(example = "Mon Aug 29 08:00:00 SGT 2016", required = true, value = "")
  @JsonProperty("balanceDate")
  @NotNull
  public LocalDate getBalanceDate() {
    return balanceDate;
  }
  public void setBalanceDate(LocalDate balanceDate) {
    this.balanceDate = balanceDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Balance balance = (Balance) o;
    return Objects.equals(available, balance.available) &&
        Objects.equals(ledger, balance.ledger) &&
        Objects.equals(balanceDate, balance.balanceDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(available, ledger, balanceDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Balance {\n");
    
    sb.append("    available: ").append(toIndentedString(available)).append("\n");
    sb.append("    ledger: ").append(toIndentedString(ledger)).append("\n");
    sb.append("    balanceDate: ").append(toIndentedString(balanceDate)).append("\n");
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

