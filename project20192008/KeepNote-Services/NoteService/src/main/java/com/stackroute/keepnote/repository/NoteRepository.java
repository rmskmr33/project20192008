package com.stackroute.keepnote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.model.Note;

/*
* This class is implementing the JpaRepository interface for Note.
* Annotate this class with @Repository annotation
* */
@Repository
public interface NoteRepository extends JpaRepository<Note, Integer>   {

}
