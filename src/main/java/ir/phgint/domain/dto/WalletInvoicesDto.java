package ir.phgint.domain.dto;

import java.util.UUID;

public class WalletInvoicesDto {

    // ~ Instance fields
    // ================================================================================================
    private long id;
    private String username = "";
    private String userMobile = "";
    private String userFullName = "";
    private long merchantId;
    private String merchantFullName = "";
    private String merchantMobile = "";
    private String merchantDebitCardPan = "";
    private String type = "";
    private double amount;
    private String timestamp;

    private UUID userId;


    // ~ Constructors
    // ===================================================================================================
    public WalletInvoicesDto() {
    }

    // ~ Methods
    // ===================================================================================================
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getMerchantFullName() {
        return merchantFullName;
    }

    public void setMerchantFullName(String merchantFullName) {
        this.merchantFullName = merchantFullName;
    }

    public String getMerchantMobile() {
        return merchantMobile;
    }

    public void setMerchantMobile(String merchantMobile) {
        this.merchantMobile = merchantMobile;
    }

    public String getMerchantDebitCardPan() {
        return merchantDebitCardPan;
    }

    public void setMerchantDebitCardPan(String merchantDebitCardPan) {
        this.merchantDebitCardPan = merchantDebitCardPan;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "WalletInvoicesDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userMobile='" + userMobile + '\'' +
                ", userFullName='" + userFullName + '\'' +
                ", merchantId=" + merchantId +
                ", merchantFullName='" + merchantFullName + '\'' +
                ", merchantMobile='" + merchantMobile + '\'' +
                ", merchantDebitCardPan='" + merchantDebitCardPan + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", timestamp='" + timestamp + '\'' +
                ", userId=" + userId +
                '}';
    }
}
