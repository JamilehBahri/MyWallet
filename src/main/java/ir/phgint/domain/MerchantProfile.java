package ir.phgint.domain;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="MerchantProfile")
@Access(AccessType.FIELD)
public class MerchantProfile {

    // ~ Instance fields
    // ================================================================================================
    @Id
    @GeneratedValue(generator="MERCHANT_SEQUENCE_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @SequenceGenerator(name="MERCHANT_SEQUENCE_GENERATOR",
            sequenceName="MERCHANT_SEQUENCE", initialValue=100000, allocationSize = 100)
    @Column(name = "MerchantId", unique = true, nullable = false)

    private long merchantId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Family", nullable = false)
    private String family;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Mobile", nullable = false)
    private String mobile;

    @Column(name = "NationalId", nullable = false)
    private String nationalId;

    @Column(name = "DebitCardPan", nullable = false, unique = true)
    private String debitCardPan;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RegistrationTimestamp")
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

    public void merchantProfileCopyFrom(final MerchantProfile merchantProfile) {
        this.merchantId = merchantProfile.merchantId;
        this.name = merchantProfile.name;
        this.family = merchantProfile.family;
        this.address = merchantProfile.address;
        this.email = merchantProfile.email;
        this.phone = merchantProfile.phone;
        this.mobile = merchantProfile.mobile;
        this.nationalId = merchantProfile.nationalId;
        this.debitCardPan = merchantProfile.debitCardPan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MerchantProfile that = (MerchantProfile) o;

        return merchantId == that.merchantId;
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
