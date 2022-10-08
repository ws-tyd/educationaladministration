package com.hngy.educationaladministration.entity.dto;

import lombok.Data;

@Data
public class TextBook {

    private String textbookName;
    private String publishingUnitAndTime;
    private String chiefEditor;
    private String jointlyEdited;
    private String planningTextbook;
    private String schoolBasedTextbook;
    private String other;
}
