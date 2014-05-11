package com.example.jwlrybox;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.*;
import android.view.inputmethod.EditorInfo;

import java.util.List;

import com.example.jwlrybox.Contact;
import com.example.jwlrybox.DatabaseHandler;

import com.example.jwlrybox.ImageAdapter;

import android.widget.AdapterView.OnItemClickListener;

public class AddScreen extends Activity implements OnItemSelectedListener {

	Button btnStore;
	EditText txtDesc;
	EditText txtWeight;

	EditText txtPrice;
	Spinner spinner;
	String category;

	TextView imgBtnText;
	static int count = 1;
	Bitmap bm;
	DatabaseHandler db = new DatabaseHandler(this);

	ImageButton iv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_screen);

		setTitle("Add Jewellery");
		iv = (ImageButton) findViewById(R.id.imgBtn);
		imgBtnText = (TextView) findViewById(R.id.textView5);

		super.onCreate(savedInstanceState);

		spinner = (Spinner) findViewById(R.id.planets_spinner);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.category, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(this);

		txtDesc = (EditText) findViewById(R.id.txtDesc);
		txtWeight = (EditText) findViewById(R.id.txtWeight);
		txtPrice = (EditText) findViewById(R.id.txtPrice);

	}

	public void img(View V) {
		Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(i, 0);

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		count++;
		super.onActivityResult(requestCode, resultCode, data);

		bm = (Bitmap) data.getExtras().get("data");
		iv.setImageBitmap(bm);
		imgBtnText.setText("");

	}

	public void storeJewellery(View v) {

		if (txtDesc.getText().length() == 0
				|| txtWeight.getText().length() == 0
				|| txtPrice.getText().length() == 0) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);

			// set title
			alertDialogBuilder.setTitle("Alert");

			// set dialog message
			alertDialogBuilder
					.setMessage("Fill Al Information.")
					.setCancelable(false)

					.setNegativeButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									// if this button is clicked, just close
									// the dialog box and do nothing
									dialog.cancel();
								}
							});

			// create alert dialog
			AlertDialog alertDialog = alertDialogBuilder.create();

			// show it
			alertDialog.show();
		}

		else {

			spinner.setOnItemSelectedListener(this);

			String desc = txtDesc.getText().toString();
			String weight = txtWeight.getText().toString();
			String price = txtPrice.getText().toString();

			DatabaseHandler db = new DatabaseHandler(this);

			// Contact im = new Contact(bm,"img"+count);
			// db.createImgEntry(im);

			db.addContact(new Contact(desc, category, weight, price),
					new Contact(bm));

			Intent myIntent = new Intent(AddScreen.this, AddViewScreen.class);
			startActivity(myIntent);

			Log.d("Reading: ", "Reading all contacts..");
			List<Contact> contacts = db.getAllContacts();

			for (Contact cn : contacts) {
				String log = "Id: " + cn.getID() + " ,Desc: " + cn.getDesc()
						+ " ,Category: " + cn.getCategory() + ", Weight:"
						+ cn.getWeight() + ", Price:" + cn.getPrice()
						+ ",Image:" + cn.getBitmap();
				// Writing Contacts to log
				Log.d("Name: ", log);
			}

		}
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		category = parent.getItemAtPosition(pos).toString();
		// System.out.println(str);

	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
	}

}
