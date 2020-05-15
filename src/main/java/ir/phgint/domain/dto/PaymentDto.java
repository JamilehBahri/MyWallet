package ir.phgint.domain.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PaymentDto {

    @Size(min=4, max=12, message="{Size.UserProfile.username.validation}")
    private String username;
    private double amount;
    private long merchantcode;
    private String merchantname;
    private String merchantfamily;

    public long getMerchantcode() {
        return merchantcode;
    }

    public void setMerchantcode(long merchantcode) {
        this.merchantcode = merchantcode;
    }

    public String getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname;
    }

    public String getMerchantfamily() {
        return merchantfamily;
    }

    public void setMerchantfamily(String merchantfamily) {
        this.merchantfamily = merchantfamily;
    }

    public PaymentDto(String username, double amount, long merchantcode, String merchantname, String merchantfamily) {
        this.username = username;
        this.amount = amount;
        this.merchantcode = merchantcode;
        this.merchantname = merchantname;
        this.merchantfamily = merchantfamily;
    }

    public PaymentDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getAmount() {

        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



}
