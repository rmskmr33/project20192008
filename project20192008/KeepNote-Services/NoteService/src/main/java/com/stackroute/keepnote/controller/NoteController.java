package com.stackroute.keepnote.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.service.NoteService;
import com.stackroute.keepnote.view.NoteView;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 */
@RestController
@RequestMapping("/api/v1")
public class NoteController {

	/*
	 * Autowiring should be implemented for the NoteService. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword
	 */

	private NoteService noteService;

	@Autowired
	public NoteController(NoteService noteService) {

		this.noteService = noteService;

	}

	/*
	 * Define a handler method which will create a specific note by reading the
	 * Serialized object from request body and save the note details in the
	 * database.This handler method should return any one of the status messages
	 * basis on different situations: 1. 201(CREATED) - If the note created
	 * successfully. 2. 409(CONFLICT) - If the noteId conflicts with any existing
	 * user.
	 * 
	 * This handler method should map to the URL "/api/v1/note" using HTTP POST
	 * method
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/notes")
	public ResponseEntity addNote(@RequestBody NoteView input) {

		ResponseEntity responseEntity = null;

		Note note = new Note();
		note.setNoteContent(input.getText());
		note.setNoteTitle(input.getTitle());
		note.setNoteStatus(input.getState());
		note.setCreatedAt(LocalDateTime.now());
		note.setCreatedBy(input.getUserName());

		if (noteService.createNote(note)) {
			NoteView view = new NoteView();
			view.setId(note.getNoteId());
			view.setState(note.getNoteStatus());
			view.setTitle(note.getNoteTitle());
			view.setText(note.getNoteContent());

			responseEntity = new ResponseEntity(view, HttpStatus.CREATED);
		} else {
			responseEntity = new ResponseEntity("Exception While adding the note...", HttpStatus.CONFLICT);
		}

		return responseEntity;
	}

	/*
	 * Define a handler method which will delete a note from a database. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the note deleted successfully from
	 * database. 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 *
	 * This handler method should map to the URL "/api/v1/note/{id}" using HTTP
	 * Delete method" where "id" should be replaced by a valid noteId without {}
	 */
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/note/{id}")
	public ResponseEntity deleteNote(@PathVariable() int id) {

		ResponseEntity responseEntity = null;

		if (noteService.deleteNote(id)) {

			responseEntity = new ResponseEntity<>("Note deleted Successfully...", HttpStatus.OK);

		} else {

			responseEntity = new ResponseEntity<>("Exception While deleting the note...", HttpStatus.NOT_FOUND);

		}

		return responseEntity;
	}

	/*
	 * Define a handler method which will update a specific note by reading the
	 * Serialized object from request body and save the updated note details in a
	 * database. This handler method should return any one of the status messages
	 * basis on different situations: 1. 200(OK) - If the note updated successfully.
	 * 2. 404(NOT FOUND) - If the note with specified noteId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/note/{id}" using HTTP PUT
	 * method.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/notes/{noteId}")
	public ResponseEntity updateNote(@PathVariable() int noteId, @RequestBody NoteView input) {

		ResponseEntity responseEntity = null;

		try {

			Note note = new Note();
			note.setNoteId(noteId);
			note.setNoteContent(input.getText());
			note.setNoteTitle(input.getTitle());
			note.setNoteStatus(input.getState());
			note.setCreatedAt(LocalDateTime.now());
			note.setCreatedBy(input.getUserName());

			Note updatedNote = noteService.updateNote(note);

			if (null != updatedNote && updatedNote.getNoteId() > 0) {
				responseEntity = new ResponseEntity<>(input, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>(input, HttpStatus.BAD_REQUEST);
			}

		} catch (NoteNotFoundExeption exe) {

			responseEntity = new ResponseEntity("Exception While Updating the Note...", HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

	/*
	 * Define a handler method which will get us the all notes by a userId. This
	 * handler method should return any one of the status messages basis on
	 * different situations: 1. 200(OK) - If the note found successfully.
	 * 
	 * This handler method should map to the URL "/api/v1/note" using HTTP GET
	 * method
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/note/{noteId}")
	public ResponseEntity getNoteById(@PathVariable() int noteId) {

		ResponseEntity responseEntity = null;

		try {

			Note note = noteService.getNoteById(noteId);

			responseEntity = new ResponseEntity(note, HttpStatus.OK);

		} catch (NoteNotFoundExeption exe) {

			responseEntity = new ResponseEntity("Exception While get Note by noteId...", HttpStatus.NOT_FOUND);
		}

		return responseEntity;
	}

	/*
	 * Define a handler method which will show details of a specific note created by
	 * specific user. This handler method should return any one of the status
	 * messages basis on different situations: 1. 200(OK) - If the note found
	 * successfully. 2. 404(NOT FOUND) - If the note with specified noteId is not
	 * found. This handler method should map to the URL
	 * "/api/v1/note/{userId}/{noteId}" using HTTP GET method where "id" should be
	 * replaced by a valid reminderId without {}
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/notes")
	public ResponseEntity getAllNotes() {

		ResponseEntity responseEntity = null;

		List<Note> userNotes = noteService.getAllNotes();

		if (userNotes != null) {

			List<NoteView> noteList = new ArrayList<NoteView>();

			for (Note note : userNotes) {
				NoteView view = new NoteView();
				view.setId(note.getNoteId());
				view.setState(note.getNoteStatus());
				view.setTitle(note.getNoteTitle());
				view.setText(note.getNoteContent());
				view.setUserName(note.getCreatedBy());

				noteList.add(view);
			}

			responseEntity = new ResponseEntity(noteList, HttpStatus.OK);

		} else {
			responseEntity = new ResponseEntity<>("Error While getting All Notes...", HttpStatus.OK);
		}

		return responseEntity;
	}

}
