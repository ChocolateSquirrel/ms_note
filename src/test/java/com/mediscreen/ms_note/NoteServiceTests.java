package com.mediscreen.ms_note;

import com.mediscreen.ms_note.model.Note;
import com.mediscreen.ms_note.repository.NoteRepository;
import com.mediscreen.ms_note.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class NoteServiceTests {

    @MockBean
    private NoteRepository noteRepository;

    @Autowired
    private NoteService noteService;


    @Test
    public void getAllNotes() {
        List<Note> notes = new ArrayList<>();
        Note note1 = new Note();
        Note note2 = new Note();
        notes.add(note1);
        notes.add(note2);
        when(noteRepository.findAll()).thenReturn(notes);
        assertEquals(2, noteService.getAllNotes().size());
    }

    @Test
    public void getNoteOK() {
        Note noteSaved = new Note("2", "Manger");
        when(noteRepository.findById(anyString())).thenReturn(Optional.of(noteSaved));
        Note note = noteService.getNote(noteSaved.getId());
        assertEquals("2", note.getPatientId());
        assertEquals("Manger", note.getRecommendations());
    }

    @Test
    public void getNoteNOK() {
        when(noteRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> noteService.getNote("id"), "Note not found id");
    }

    @Test
    public void deleteNoteNOK() {
        when(noteRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> noteService.deleteNote("id"), "Note not found id");
    }

    @Test
    public void updateNoteOK() {
        List<Note> notes = new ArrayList<>();
        Note note1 = new Note("1", "Dormir");
        Note note2 = new Note("2", "Manger");
        notes.add(note1);
        notes.add(note2);
        Note noteUpdated = new Note("1", "Faire du sport");

        when(noteRepository.findById("1")).thenReturn(Optional.of(note1));
        Note noteFinal = noteService.updateNote("1", noteUpdated);

        assertEquals("1", noteFinal.getPatientId());
        assertEquals("Faire du sport", noteFinal.getRecommendations());
    }

    @Test
    public void getPatientNotes() {
        List<Note> notes = new ArrayList<>();
        Note note2 = new Note("2", "Manger");
        Note note3 = new Note("2", "Faire du sport");
        notes.add(note2);
        notes.add(note3);

        when(noteRepository.findAllByPatientId("2")).thenReturn(notes);
        assertEquals(2, noteService.getPatientNotes("2").size());
    }

    @Test
    public void addPatientNote() {
        List<Note> notes = new ArrayList<>();
        Note note1 = new Note("2", "Dormir");
        Note note2 = new Note("2", "Manger");
        notes.add(note1);
        notes.add(note2);
        Note noteToAdd = new Note("2", "Faire du sport");
        when(noteRepository.findAllByPatientId("2")).thenReturn(notes);
        noteService.addPatientNote("2", noteToAdd);
        assertEquals(3, noteService.getPatientNotes("2").size());
    }

}
