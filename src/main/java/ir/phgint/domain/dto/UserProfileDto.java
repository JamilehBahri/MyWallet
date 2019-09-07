package ir.phgint.domain.dto;

import ir.phgint.domain.Gender;
import ir.phgint.validation.DuplecateEmail;
import ir.phgint.validation.DuplecateUsername;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

@XmlRootElement
@DuplecateUsername (message = "{UserProfile.username.validation.Duplecate}")
@DuplecateEmail(message = "{UserProfile.email.validation.Duplicate}")
public class UserProfileDto implements ValidationType , Serializable {

    private UUID id;

    @NotEmpty(message="{Empty.UserProfile.name.validation}")
    @NotNull
    @Size(min=4, max=50, message="{Size.UserProfile.name.validation}")
    private String name;

    @NotEmpty(message="{Empty.UserProfile.family.validation}")
    @NotNull
    @Size(min=4, max=50, message="{Size.UserProfile.family.validation}")
    private String family;

    @NotNull(message="{Empty.UserProfile.gender.validation}")
    private Gender gender;

    @Size(min=4, max=50, message="{Size.UserProfile.Address.validation}")
    private String address;

    @Pattern(regexp="(^$|[0-9]{8})" , message="{Pattern.UserProfile.phone.validation}")
    private String phone;

    @Pattern(regexp="(^$|[0-9]{10})" , message="{Pattern.UserProfile.mobile.validation}")
    private String mobile;

//    @NotNull
//    @NotEmpty(message="{Empty.UserProfile.email.validation}")
    @Size(min = 6, max = 50 , message="{Size.UserProfile.email.validation}")
//    @Pattern(regexp="^([a-zA-Z0-9\\-\\.\\_]+)'+'(\\@)([a-zA-Z0-9\\-\\.]+)'+'(\\.)([a-zA-Z]{2,4})$"
//            , message = "{Pattern.UserProfile.email.validation}")
    private String email;

//    @Future(message="{Future.UserProfile.birthday.validation}")
    private String birthday;

    @Pattern(regexp="\\d{10}" , message = "{Size.UserProfile.nationalId.validation}")
    private String nationalId;

    @Size(min=4, max=12, message="{Size.UserProfile.username.validation}")
    private String username;

    private String password;
    private String passwordConfirm;

    @NotNull(message="{Empty.UserProfile.role.validation}")
    private String role;

    private double balance;

    // ~ Constructors
    // ===================================================================================================
    public UserProfileDto() { }

    public UserProfileDto(UUID id,String name, String family, Gender gender,
                          String address, String phone, String mobile,
                          String email, String birthday, String nationalId,
                          String username, String password, String role, double balance) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.mobile = mobile;
        this.email = email;
        this.birthday = birthday;
        this.nationalId = nationalId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.balance = balance;
    }

    // ~ Methods
    // ===================================================================================================
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

    public String getBirthday() {

        return birthday;
    }

    public void setBirthday(String birthday) {
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserProfileDto{" +
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
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", role=" + role +
                ", balance=" + balance +
                '}';
    }
}
