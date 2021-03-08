package com.doroshenko.serhey.person.domain.person;

import com.doroshenko.serhey.person.domain.core.base.BaseEntity;
import com.doroshenko.serhey.person.enumeration.person.Gender;
import com.doroshenko.serhey.person.enumeration.person.PersonType;
import com.vladmihalcea.hibernate.type.array.EnumArrayType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;

import static com.vladmihalcea.hibernate.type.array.internal.AbstractArrayType.SQL_ARRAY_TYPE;

/**
 * Database representation of person
 *
 * @author Serhey Doroshenko
 * @see BaseEntity
 */
@Entity
@Table(name = "person")
@TypeDef(typeClass = EnumArrayType.class, defaultForType = PersonType[].class, parameters = {@Parameter(value = "person_type_enum", name = SQL_ARRAY_TYPE)})
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
    @SuppressWarnings("JpaAttributeTypeInspection")
    @Column(name = "person_types", columnDefinition = "person_type_enum[]")
    private PersonType[] personTypes;

    /* Util methods */
//    public Set<PersonType> getPersonTypeSet() {
//        if (personTypes == null) return Collections.emptySet();
//        return Arrays.stream(personTypes).collect(Collectors.toSet());
//    }
//
//    public void setPersonTypes(final Set<PersonType> personTypes) {
//        if (personTypes == null) setPersonTypes(new PersonType[0]);
//        else setPersonTypes(personTypes.toArray(PersonType[]::new));
//    }

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

    public PersonType[] getPersonTypes() {
        return personTypes;
    }

    public void setPersonTypes(PersonType[] personTypes) {
        this.personTypes = personTypes;
    }

}
