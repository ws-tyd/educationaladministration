package com.hngy.educationaladministration.entity;

import lombok.Builder;
import lombok.Data;

@Data
public class User {

    private String userName;

    private String role;

    private Long id;
}
