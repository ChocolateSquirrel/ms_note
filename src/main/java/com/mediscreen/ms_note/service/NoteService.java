package com.mediscreen.ms_note.service;

import com.mediscreen.ms_note.model.Note;
import com.mediscreen.ms_note.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Note addNote(Note note){
        return noteRepository.insert(note);
    }

    public Note getNote(String id){
        return noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Note not found: " + id));
    }

    public Note deleteNote(String id){
        Note noteToDelete = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Note not found: " + id));
        noteRepository.delete(noteToDelete);
        return noteToDelete;
    }

    public Note updateNote(String id, Note note){
        Note noteToUpdate = noteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Note not found: " + id));
        noteToUpdate.setPatientId(note.getPatientId());
        noteToUpdate.setRecommendations(note.getRecommendations());
        noteRepository.save(noteToUpdate);
        return noteToUpdate;
    }
}
