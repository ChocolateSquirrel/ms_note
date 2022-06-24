package com.mediscreen.ms_note.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Note {
    @Id
    private String id;
    private String patId;
    private String recommendations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patId;
    }

    public void setPatientId(String patId) {
        this.patId = patId;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public Note(String patId, String recommendations) {
        this.patId = patId;
        this.recommendations = recommendations;
    }
}
