package com.stackroute.keepnote.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.repository.NoteRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class NoteServiceImpl implements NoteService {

	/*
	 * Autowiring should be implemented for the NoteRepository and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	private NoteRepository noteRepository;

	@Autowired
	public NoteServiceImpl(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;

	}

	/*
	 * This method should be used to save a new note.
	 */
	public boolean createNote(Note note) {

		boolean createdStatus = false;

		if (noteRepository.save(note) != null) {

			createdStatus = true;
		}

		return createdStatus;
	}

	/* This method should be used to delete all notes with specific userId. */

	public boolean deleteNote(int noteId) {

		boolean deleteStatus = false;


		try {

			Note note = noteRepository.getOne(noteId);

			if (note != null) {
				
				noteRepository.delete(note);

				deleteStatus = true;

			} else {
				throw new NoteNotFoundExeption("Note Not Found Exception...");
			}

		} catch (NoSuchElementException exe) {

		} catch (NoteNotFoundExeption exc) {

		}

		return deleteStatus;
	}

	/*
	 * This method should be used to update a existing note.
	 */
	public Note getNoteById(int noteId) throws NoteNotFoundExeption {

		Note note = null;
		try {
			
			note = noteRepository.getOne(noteId);

		} catch (NoSuchElementException exception) {

			throw new NoteNotFoundExeption("Note does not exists...");
		}

		return note;
	}

	/*
	 * This method should be used to update a existing note.
	 */
	public Note updateNote(Note note) throws NoteNotFoundExeption {

		try {
			
			if (noteRepository.existsById(note.getNoteId())) {
				noteRepository.save(note);
			}

		} catch (NoSuchElementException exception) {

			throw new NoteNotFoundExeption("Note does not exists...");
		}

		return note;
	}

	/*
	 * This method should be used to get all notes with specific userId.
	 */
	public List<Note> getAllNotes() {

		List<Note> allNotes = noteRepository.findAll();

		return allNotes;
	}

}
