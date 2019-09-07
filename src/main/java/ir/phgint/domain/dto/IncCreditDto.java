package ir.phgint.domain.dto;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IncCreditDto {

    @Size(min=4, max=12, message="{Size.UserProfile.username.validation}")
    private String username;
    private double amount;
    private String name;
    private String family;

    public IncCreditDto(String username, double amount, String name, String family) {
        this.username = username;
        this.amount = amount;
        this.name = name;
        this.family = family;
    }

    public IncCreditDto() {
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
}
