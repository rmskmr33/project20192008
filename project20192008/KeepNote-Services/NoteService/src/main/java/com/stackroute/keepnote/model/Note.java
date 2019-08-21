package com.stackroute.keepnote.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */
@Entity
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int noteId;
	private String noteTitle;
	private String noteContent;
	@Column(nullable = false)
	private String noteStatus;
	private LocalDateTime createdAt;
	private String createdBy;

	public Note() {
	}

	public Note(int noteId, String noteTitle, String noteContent, String noteStatus, LocalDateTime createdAt,
			String createdBy) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteStatus = noteStatus;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}

	public int getNoteId() {
		return noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public String getNoteStatus() {
		return noteStatus;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Note)) {
			return false;
		}
		Note note = (Note) o;
		return getNoteId() == note.getNoteId() && getNoteTitle().equals(getNoteTitle())
				&& getNoteContent().equals(getNoteContent()) && getNoteStatus().equals(getNoteStatus());
	}

}