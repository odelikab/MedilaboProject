package com.medilaboNotes.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.medilaboNotes.model.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
	public List<Note> findByPatId(Integer id);

	public List<Note> findByPatientAndPatId(String patient, Integer patid);

	@Query(value = "{ 'patient' : ?0, 'note':{$regex : ?1, $options: 'i'}}", fields = "{patId : 1, patient : 1, note : 1}")
	public List<Note> findPatientAndNote(String patient, String decl);

	public List<Note> findByPatientAndNoteContaining(String patient, String decl);

	@Aggregation("{ $group: { _id: '$patient', count: { $push: '$patient' }}}")
	public List<Object> groupByPatient();

	@Aggregation("{ '$project': { '_id' : '$note' } }")
	public List<String> findAllNotes();
}
