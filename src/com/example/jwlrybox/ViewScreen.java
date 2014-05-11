package com.example.jwlrybox;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import com.example.jwlrybox.Contact;
import com.example.jwlrybox.DatabaseHandler;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class ViewScreen extends Activity {

	ArrayList<Contact> image_details;
	TextView title;
	String param1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_screen);

		DatabaseHandler dbHelper = new DatabaseHandler(
				this.getApplicationContext());
		Bundle bundle = this.getIntent().getExtras();
		param1 = bundle.getString("param1");
		setTitle(param1);

		if (param1.equalsIgnoreCase("All")) {
			image_details = dbHelper.getAllContacts();
		} else {
			image_details = dbHelper.getCategoryWise(param1);

		}

		if (image_details.size() == 0) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);

			// set title
			alertDialogBuilder.setTitle("Alert");

			// set dialog message
			alertDialogBuilder
					.setMessage("No Items Stored in this Category.")
					.setCancelable(false)

					.setNegativeButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

									Intent myIntent = new Intent(
											ViewScreen.this,
											AddViewScreen.class);
									startActivity(myIntent);

									dialog.cancel();
								}
							});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();

		}
		final ListView lv1 = (ListView) findViewById(R.id.listV_main);
		lv1.setAdapter(new ListViewAdapter(this, image_details));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_screen, menu);
		return true;
	}

}
