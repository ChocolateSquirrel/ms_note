package com.mediscreen.ms_note.controller;

import com.mediscreen.ms_note.model.Note;
import com.mediscreen.ms_note.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @PostMapping("/")
    public Note addNote(@RequestParam String patId, @RequestParam String recommendations){
        Note note = new Note(patId, recommendations);
        return noteService.addNote(note);
    }

    @GetMapping("/{id}")
    public Note getNote(@PathVariable String id) {
        return noteService.getNote(id);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable String id, @RequestBody Note note) {
        Note NoteUpdated = noteService.updateNote(id, note);
        return NoteUpdated;
    }

    @DeleteMapping("/{id}")
    public Note delete(@PathVariable String id) {
        Note NoteDeleted = noteService.deleteNote(id);
        return NoteDeleted;
    }
}
