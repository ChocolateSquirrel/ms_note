package com.mediscreen.ms_note.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Document(collection = "notes")
public class Note {
    @MongoId
    private String id;

    @NotBlank
    private String patientId;

    @NotBlank
    private String recommendations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patId) {
        this.patientId = patId;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public Note() {}

    public Note(String patId, String recommendations) {
        id = UUID.randomUUID().toString();
        this.patientId = patId;
        this.recommendations = recommendations;
    }
}
