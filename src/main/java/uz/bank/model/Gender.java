package uz.bank.model;

import lombok.Getter;

@Getter
public enum Gender {
    MALE,
    FEMALE;

    private String value;

    Gender() {
    }
}
