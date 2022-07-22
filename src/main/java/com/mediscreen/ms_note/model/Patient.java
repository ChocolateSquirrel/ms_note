package com.mediscreen.ms_note.model;

import lombok.Data;

@Data
public class Patient {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String datesOfBirth;
    private final Sex sex;
    private final String address;
    private final String phone;

    public Patient(int id, String firstName, String lastName, String dateOfBirth, String datesOfBirth, Sex sex, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.datesOfBirth = datesOfBirth;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
    }
}
