package ir.insurance.startup.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import ir.insurance.startup.domain.enumeration.NoeShakhs;

/**
 * A Ashkhas.
 */
@Entity
@Table(name = "ashkhas")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Ashkhas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "hire_date")
    private Instant hireDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "noe_shakhs")
    private NoeShakhs noeShakhs;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Ashkhas firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Ashkhas lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Ashkhas email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Ashkhas phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getHireDate() {
        return hireDate;
    }

    public Ashkhas hireDate(Instant hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public NoeShakhs getNoeShakhs() {
        return noeShakhs;
    }

    public Ashkhas noeShakhs(NoeShakhs noeShakhs) {
        this.noeShakhs = noeShakhs;
        return this;
    }

    public void setNoeShakhs(NoeShakhs noeShakhs) {
        this.noeShakhs = noeShakhs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ashkhas ashkhas = (Ashkhas) o;
        if (ashkhas.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ashkhas.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ashkhas{" +
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
