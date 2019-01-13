package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Control.
 */
@Entity
@Table(name = "control")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Control implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "control")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Driver> drivers = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Control name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public Control drivers(Set<Driver> drivers) {
        this.drivers = drivers;
        return this;
    }

    public Control addDriver(Driver driver) {
        this.drivers.add(driver);
        driver.setControl(this);
        return this;
    }

    public Control removeDriver(Driver driver) {
        this.drivers.remove(driver);
        driver.setControl(null);
        return this;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
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
        Control control = (Control) o;
        if (control.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), control.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Control{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
