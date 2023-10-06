package com.medilaboNotes.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Document(collection = "note")
public class Note {

//	@Id
	private Integer patId;
	private String patient;
	private String note;

}