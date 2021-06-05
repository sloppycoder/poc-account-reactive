package org.vino9.poc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Balance {
  private BigDecimal available = null;
  private BigDecimal ledger = null;
  private Date balanceDate = null;

  /** */
  @Schema(example = "100.01", required = true, description = "")
  @JsonProperty("available")
  @NotNull
  public BigDecimal getAvailable() {
    return available;
  }

  public void setAvailable(BigDecimal available) {
    this.available = available;
  }

  /** */
  @Schema(example = "100.02", required = true, description = "")
  @JsonProperty("ledger")
  @NotNull
  public BigDecimal getLedger() {
    return ledger;
  }

  public void setLedger(BigDecimal ledger) {
    this.ledger = ledger;
  }

  /** */
  @Schema(example = "Mon Aug 29 00:00:00 GMT 2016", required = true, description = "")
  @JsonProperty("balanceDate")
  @NotNull
  public Date getBalanceDate() {
    return balanceDate;
  }

  public void setBalanceDate(Date balanceDate) {
    this.balanceDate = balanceDate;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Balance balance = (Balance) o;
    return Objects.equals(available, balance.available)
        && Objects.equals(ledger, balance.ledger)
        && Objects.equals(balanceDate, balance.balanceDate);
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
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
