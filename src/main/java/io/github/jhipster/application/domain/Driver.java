package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.Rating;

/**
 * A Driver.
 */
@Entity
@Table(name = "driver")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "first_rating")
    private Rating firstRating;

    @Enumerated(EnumType.STRING)
    @Column(name = "second_rating")
    private Rating secondRating;

    @OneToMany(mappedBy = "driver")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Attribute> attributes = new HashSet<>();
    @OneToMany(mappedBy = "driver")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Control> controls = new HashSet<>();
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

    public Driver name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rating getFirstRating() {
        return firstRating;
    }

    public Driver firstRating(Rating firstRating) {
        this.firstRating = firstRating;
        return this;
    }

    public void setFirstRating(Rating firstRating) {
        this.firstRating = firstRating;
    }

    public Rating getSecondRating() {
        return secondRating;
    }

    public Driver secondRating(Rating secondRating) {
        this.secondRating = secondRating;
        return this;
    }

    public void setSecondRating(Rating secondRating) {
        this.secondRating = secondRating;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public Driver attributes(Set<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Driver addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
        attribute.setDriver(this);
        return this;
    }

    public Driver removeAttribute(Attribute attribute) {
        this.attributes.remove(attribute);
        attribute.setDriver(null);
        return this;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Set<Control> getControls() {
        return controls;
    }

    public Driver controls(Set<Control> controls) {
        this.controls = controls;
        return this;
    }

    public Driver addControl(Control control) {
        this.controls.add(control);
        control.setDriver(this);
        return this;
    }

    public Driver removeControl(Control control) {
        this.controls.remove(control);
        control.setDriver(null);
        return this;
    }

    public void setControls(Set<Control> controls) {
        this.controls = controls;
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
        Driver driver = (Driver) o;
        if (driver.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), driver.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Driver{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", firstRating='" + getFirstRating() + "'" +
            ", secondRating='" + getSecondRating() + "'" +
            "}";
    }
}
