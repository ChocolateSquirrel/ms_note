package com.mediscreen.ms_note.controller;

import com.mediscreen.ms_note.model.Note;
import com.mediscreen.ms_note.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/view/notes")
public class NoteControllerView {

    private final NoteService noteService;

    public NoteControllerView(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String getAllNotes(Model model) {
        log.info("Request GET : /view/notes/");
        List<Note> notesList = noteService.getAllNotes();
        model.addAttribute("notes", notesList);
        log.info("Response for /view/notes/ : there are " + notesList.size() + " notes");
        return "note/list";
    }

    @GetMapping("/{id}")
    public String getNote(@PathVariable String id, Model model) {
        log.info("Request GET : /view/notes/" + id);
        Note note = noteService.getNote(id);
        model.addAttribute("note", note);
        log.info("Response for /view/notes/" + id + " : " + note.getPatientId() + " " + note.getRecommendations());
        return "note/infoNote";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        log.info("Request GET : /view/notes/add/");
        model.addAttribute("note", new Note());
        log.info("Response : OK");
        return "note/add";
    }

    @PostMapping("/")
    public String addNote(@Valid @ModelAttribute Note note, Model model, BindingResult result){
        log.info("Request POST : /view/notes/");
        if (!result.hasErrors()){
            Note noteToAdd = new Note(note.getPatientId(), note.getRecommendations());
            noteService.addNote(noteToAdd);
            List<Note> notesList = noteService.getAllNotes();
            model.addAttribute("notes", notesList);
            return "note/list";
        }
        log.error("Response : " + result.getErrorCount() + " errors");
        return "note/add";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable String id, Model model){
        log.info("Request GET : /view/notes/update/" + id);
        Note note = noteService.getNote(id);
        model.addAttribute("note", note);
        log.info("Response for /view/patients/update/" + id + " : note for patient id " + note.getPatientId() + " is going to be updated");
        return "note/update";
    }

    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable String id, @Valid @ModelAttribute Note note, Model model, BindingResult result) {
        log.info("Request POST : /view/notes/update/" + id);
        if(!result.hasErrors()){
            noteService.updateNote(id, note);
            List<Note> notesList = noteService.getAllNotes();
            model.addAttribute("notes", notesList);
            return "note/list";
        }
        log.error("Response : " + result.getErrorCount() + " errors");
        return "note/update";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, Model model) {
        log.info("Request GET : /view/patients/delete/" + id);
        noteService.deleteNote(id);
        List<Note> notesList = noteService.getAllNotes();
        model.addAttribute("notes", notesList);
        log.info("Response for /view/notes/ : there are " + notesList.size() + " notes");
        return "note/list";
    }

}
