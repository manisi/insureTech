package ir.insurance.startup.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import ir.insurance.startup.domain.enumeration.NoeShakhs;

/**
 * A DTO for the Ashkhas entity.
 */
public class AshkhasDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Instant hireDate;

    private NoeShakhs noeShakhs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getHireDate() {
        return hireDate;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public NoeShakhs getNoeShakhs() {
        return noeShakhs;
    }

    public void setNoeShakhs(NoeShakhs noeShakhs) {
        this.noeShakhs = noeShakhs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AshkhasDTO ashkhasDTO = (AshkhasDTO) o;
        if (ashkhasDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ashkhasDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AshkhasDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", noeShakhs='" + getNoeShakhs() + "'" +
            "}";
    }
}
