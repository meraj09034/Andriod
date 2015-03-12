package com.ftfl.icareapp;

import com.ftfl.icareapp.database.ImportantNotesDataSource;
import com.ftfl.icareapp.model.ImportentNotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class importantNotesDetailShowActivity extends Activity {

	TextView tvTitle = null;
	TextView tvData = null;
	TextView tvDescription = null;

	ImportantNotesDataSource mNotesDS = null;
	ImportentNotes mUpdateNotes = null;
	String mTitle;
	String mDescription;
	String mDate;
	String mID = "";
	Long mLId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_detail_importent_note);

		tvTitle = (TextView) findViewById(R.id.view_note_titel);
		tvData = (TextView) findViewById(R.id.view_note_date);
		tvDescription = (TextView) findViewById(R.id.view_note_description);

		Intent mActivityIntent = getIntent();
		mID = mActivityIntent.getStringExtra("id");

		if (mID != null) {
			mLId = Long.parseLong(mID);

			/*
			 * get the activity which include all data from database according
			 * hospitalId of the clicked item.
			 */
			mNotesDS = new ImportantNotesDataSource(this);
			mUpdateNotes = mNotesDS.singleImportentNotesData(mLId);

			mTitle = mUpdateNotes.getNotesTitel();
			mDescription = mUpdateNotes.getNotesDescription();
			mDate = mUpdateNotes.getNotesDate();

			// set the value of database to the text field.

			tvTitle.setText(mTitle);
			tvData.setText(mDate);
			tvDescription.setText(mDescription);
		}

	}
}
