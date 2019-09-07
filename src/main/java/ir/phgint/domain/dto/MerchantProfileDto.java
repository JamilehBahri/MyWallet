package ir.phgint.domain.dto;

import ir.phgint.domain.MerchantProfile;
import ir.phgint.domain.WalletInvoices;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class MerchantProfileDto implements ValidationType , Serializable {

    private long merchantId;

    @NotEmpty(message="{Empty.MerchantProfile.name.validation}")
    @Size(min=4, max=50, message="{Size.MerchantProfile.name.validation}")
    private String name;

    @NotEmpty(message="{Empty.MerchantProfile.name.validation}")
    @Size(min=4, max=50, message="{Size.MerchantProfile.family.validation}")
    private String family;

    //    @NotNull
//    @NotEmpty(message="{Empty.UserProfile.email.validation}")
    @Size(min = 1, max = 30 ,message = "{Size.MerchantProfile.email.validation}")
//    @Pattern(regexp="^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$"
//            , message = "{Pattern.UserProfile.email.validation}")
    private String email;

    @Size(min=4, max=50, message="{Size.MerchantProfile.Address.validation}")
    private String address;

    @Pattern(regexp="(^$|[0-9]{8})" , message="{Pattern.MerchantProfile.phone.validation}")
    private String phone;

    @Pattern(regexp="(^$|[0-9]{10})" , message="{Pattern.MerchantProfile.mobile.validation}")
    private String mobile;

    @Pattern(regexp="\\d{10}" , message = "{Size.MerchantProfile.nationalId.validation}")
    private String nationalId;

    @Pattern(regexp="\\d{16}" , message = "{Size.MerchantProfile.nationalId.validation}")
    private String debitCardPan;

    private Date registrationTimestamp;

    @OneToMany(mappedBy = "merchantProfile", fetch = FetchType.EAGER)
    private List<WalletInvoices> walletInvoices = new ArrayList<>();

    // ~ Methods
    // ===================================================================================================
    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getDebitCardPan() {
        return debitCardPan;
    }

    public void setDebitCardPan(String debitCardPan) {
        this.debitCardPan = debitCardPan;
    }

    public Date getRegistrationTimestamp() {
        return registrationTimestamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistrationTimestamp(Date registrationTimestamp) {
        this.registrationTimestamp = registrationTimestamp;
    }

    public List<WalletInvoices> getWalletInvoices() {
        return walletInvoices;
    }

    public void setWalletInvoices(List<WalletInvoices> walletInvoices) {
        this.walletInvoices = walletInvoices;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MerchantProfile that = (MerchantProfile) o;

        return merchantId == that.getMerchantId();
    }

    @Override
    public int hashCode() {
        return (int) (merchantId ^ (merchantId >>> 32));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MerchantProfile{");
        sb.append("merchantId=").append(merchantId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", family='").append(family).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", nationalId='").append(nationalId).append('\'');
        sb.append(", debitCardPan='").append(debitCardPan).append('\'');
        sb.append(", registrationTimestamp=").append(registrationTimestamp);
        sb.append(", walletInvoices=").append(walletInvoices);
        sb.append('}');
        return sb.toString();
    }
}