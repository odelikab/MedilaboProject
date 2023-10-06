package com.medilaboFront.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medilaboFront.beans.NoteBean;
import com.medilaboFront.beans.PatientBean;
import com.medilaboFront.proxies.MicroserviceNoteProxy;
import com.medilaboFront.proxies.MicroservicePatientProxy;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	MicroservicePatientProxy patientProxy;

	@Autowired
	MicroserviceNoteProxy noteProxy;

	@GetMapping("/home")
	public String home(Model model) {

		List<PatientBean> patients = patientProxy.listeDesPatients();
		model.addAttribute("patients", patients);
		return "home";
	}

	@GetMapping("/home/notes")
	public String homeNotes(Model model) {
		List<NoteBean> notes = noteProxy.getNotes();
		model.addAttribute("notes", notes);
		return "homeNotes";
	}

	@GetMapping("/home/notes/{id}")
	public String homePatientNotes(@PathVariable("id") Integer id, Model model) {
		List<NoteBean> notes = noteProxy.getPatientNotes(id);
		Optional<PatientBean> patient = patientProxy.getPatient(notes.get(0).getPatient());
		PatientBean patientFound = patient.get();
		model.addAttribute("notes", notes);
		model.addAttribute("patient", patientFound);
		return "add";
	}

}
