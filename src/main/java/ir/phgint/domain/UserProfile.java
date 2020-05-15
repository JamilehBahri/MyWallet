package ir.phgint.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="userprofile")
@Access(AccessType.FIELD)
public class UserProfile {

    // ~ Instance fields
    // ================================================================================================
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    @Type(type="pg-uuid")
    @Column(name = "id", unique = true)
    private UUID id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String family;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Mobile", nullable = false)
    private String mobile;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "Birthday")
    private Date birthday;

    @Column(name = "NationalId")
    private String nationalId;

    @Column(name= "username",nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "userProfile", fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.REFRESH, CascadeType.DETACH})
    private List<WalletInvoices> walletInvoices = new ArrayList<>();

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                                           CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "Id")
    private Role role;

    @Transient
    private String passwordConfirm;

    @Column(nullable = false)
    private double balance;

    // ~ Constructor
    // ===================================================================================================
    public UserProfile() { }

    // ~ Methods
    // ===================================================================================================
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<WalletInvoices> getWalletInvoices() {
        return walletInvoices;
    }

    public void setWalletInvoices(List<WalletInvoices> walletInvoices) {
        this.walletInvoices = walletInvoices;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void userProfileCopyFrom(final UserProfile userProfile) {
        this.name = userProfile.name;
        this.family = userProfile.family;
        this.gender = userProfile.gender;
        this.address = userProfile.address;
        this.phone = userProfile.phone;
        this.mobile = userProfile.mobile;
        this.email = userProfile.email;
        this.birthday = userProfile.birthday;
        this.nationalId = userProfile.nationalId;
        this.password = userProfile.password;
//        this.role = userProfile.role;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfile that = (UserProfile) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", nationalId='" + nationalId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", walletInvoices=" + walletInvoices +
                ", role=" + role +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", balance=" + balance +
                '}';
    }
}