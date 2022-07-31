package com.mediscreen.ms_note.controller;

import com.mediscreen.ms_note.model.Note;
import com.mediscreen.ms_note.service.NoteService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping("/add")
    public Note addNote(@RequestParam String patId, @RequestParam String recommendations){
        Note note = new Note(patId, recommendations);
        return noteService.addNote(note);
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable String id) {
        return noteService.getNote(id);
    }

    @PostMapping("/update/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note note) {
        Note NoteUpdated = noteService.updateNote(id, note);
        return NoteUpdated;
    }

    @PostMapping("/delete/{id}")
    public Note delete(@PathVariable String id) {
        Note NoteDeleted = noteService.deleteNote(id);
        return NoteDeleted;
    }

    @GetMapping("/{patientId}/notes/list")
    public List<Note> getPatientNotes(@PathVariable String patientId){
        return noteService.getPatientNotes(patientId);
    }

    @PostMapping("/{patientId}/note")
    public void addPatientNote(@PathVariable String patientId, @RequestBody Note note){
       noteService.addPatientNote(patientId, note);
    }
}
