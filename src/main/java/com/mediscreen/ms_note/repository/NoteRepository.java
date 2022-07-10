package com.mediscreen.ms_note.repository;

import com.mediscreen.ms_note.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findAllByPatientId(String patientId);
}
