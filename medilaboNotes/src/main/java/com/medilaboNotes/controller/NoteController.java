package com.medilaboNotes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilaboNotes.model.Note;
import com.medilaboNotes.repositories.NoteRepository;

@RestController
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	private NoteRepository noteRepository;

	@GetMapping("/findAllNotes")
	public List<Note> getNotes() {
		return noteRepository.findAll();
	}

	@PostMapping("/addNote")
	public String saveNote(@RequestBody Note note) {
		noteRepository.insert(note);
		return "Added book with id : " + note.getPatId();
	}

	@GetMapping("/findAllNotes/{id}")
	public List<Note> getBook(@PathVariable Integer id) {
		return noteRepository.findByPatId(id);
	}

	@GetMapping("/custom")
	public List<Note> getCustom(String patient) {
		String decl = "vertige";
		return noteRepository.findPatientAndNote(patient, decl);
	}

	@GetMapping("/patient")
	public Object getPatientGroups() {
		return noteRepository.groupByPatient();
	}

	@GetMapping("/notes")
	public Object getPatientNotes() {
		return noteRepository.findAllNotes();
	}
}
