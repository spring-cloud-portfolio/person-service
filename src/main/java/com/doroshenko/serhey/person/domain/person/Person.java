package com.doroshenko.serhey.person.domain.person;

import com.doroshenko.serhey.person.domain.core.base.BaseEntity;
import com.doroshenko.serhey.person.enumeration.person.Gender;
import com.doroshenko.serhey.person.enumeration.person.PersonType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;

/**
 * Database representation of person
 *
 * @author Serhey Doroshenko
 * @see BaseEntity
 */
@Entity
@Table(name = "person")
public class Person extends BaseEntity {

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "middle_name", nullable = false)
    private String middleName;
    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "type", length = 50, nullable = false)
    @CollectionTable(
            name = "person_type",
            joinColumns = @JoinColumn(name = "person_id", nullable = false)
    )
    private Set<PersonType> types;

    /* Getters and setters */
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    @NotNull
    public Set<PersonType> getTypes() {
        if (types == null) types = EnumSet.noneOf(PersonType.class);
        return types;
    }

}
