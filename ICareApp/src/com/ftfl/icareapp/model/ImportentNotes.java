package com.ftfl.icareapp.model;

public class ImportentNotes {

	String mId;
	String mNotesTitel;
	String mNotesDate;
	String mNotesDescription;
	
	
	public ImportentNotes() {
		super();
	}
	public ImportentNotes(String mId, String mNotesTitel, String mNotesDate,
			String mNotesDescription) {
		super();
		this.mId = mId;
		this.mNotesTitel = mNotesTitel;
		this.mNotesDate = mNotesDate;
		this.mNotesDescription = mNotesDescription;
	}
	public String getId() {
		return mId;
	}
	public void setId(String mId) {
		this.mId = mId;
	}
	public String getNotesTitel() {
		return mNotesTitel;
	}
	public void setNotesTitel(String mNotesTitel) {
		this.mNotesTitel = mNotesTitel;
	}
	public String getNotesDate() {
		return mNotesDate;
	}
	public void setNotesDate(String mNotesDate) {
		this.mNotesDate = mNotesDate;
	}
	public String getNotesDescription() {
		return mNotesDescription;
	}
	public void setNotesDescription(String mNotesDescription) {
		this.mNotesDescription = mNotesDescription;
	}
	
	
}
