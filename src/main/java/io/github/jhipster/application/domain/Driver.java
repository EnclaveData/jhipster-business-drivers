package io.github.jhipster.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
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

    @ManyToOne
    @JsonIgnoreProperties("drivers")
    private Attribute attribute;

    @ManyToOne
    @JsonIgnoreProperties("drivers")
    private Control control;

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

    public Attribute getAttribute() {
        return attribute;
    }

    public Driver attribute(Attribute attribute) {
        this.attribute = attribute;
        return this;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Control getControl() {
        return control;
    }

    public Driver control(Control control) {
        this.control = control;
        return this;
    }

    public void setControl(Control control) {
        this.control = control;
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
