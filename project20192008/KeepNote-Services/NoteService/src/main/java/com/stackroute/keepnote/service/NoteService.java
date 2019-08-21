package com.stackroute.keepnote.service;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;

import java.util.List;


public interface NoteService {
	
	/*
	 * Should not modify this interface. You have to implement these methods in
	 * corresponding Impl classes
	 */


    boolean createNote(Note note);

    boolean deleteNote(int noteId);
    
    Note getNoteById(int noteId) throws NoteNotFoundExeption;

    Note updateNote(Note note) throws NoteNotFoundExeption;

    List<Note> getAllNotes();


}
