package com.example.jwlrybox;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.*;

import java.util.Date;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.example.jwlrybox.*;

public class AddViewScreen extends Activity {

	String strCategory;
	GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_view_screen);

		setTitle("Jewellery Box");

		DatabaseHandler db = new DatabaseHandler(this);

		int countRing = db.getContactsCount("Ring");
		String Ring = "Ring ( " + countRing + " )";

		int countAll = db.getContactsCount("All");
		String All = "All ( " + countAll + " )";

		int countNecklace = db.getContactsCount("Necklace");
		String Necklace = "Necklace ( " + countNecklace + " )";

		int countEarings = db.getContactsCount("Earings");
		String Earings = "Earings ( " + countEarings + " )";

		int countBangles = db.getContactsCount("Bangles");
		String Bangles = "Bangles ( " + countBangles + " )";

		int countOther = db.getContactsCount("other");
		String Other = "Other ( " + countOther + " )";

		final String[] CATEGORY = new String[] { All, Ring, Necklace, Earings,
				Bangles, Other };

		gridView = (GridView) findViewById(R.id.gridView1);

		gridView.setAdapter(new ImageAdapter(this, CATEGORY));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				switch (position) {
				case 0:
					strCategory = "All";
					break;
				case 1:
					strCategory = "Ring";
					break;
				case 2:
					strCategory = "Necklace";
					break;
				case 3:
					strCategory = "Earings";
					break;
				case 4:
					strCategory = "Bangles";
					break;
				case 5:
					strCategory = "other";
					break;

				}
				Bundle bundle = new Bundle();
				bundle.putString("param1", strCategory);

				Intent myIntent = new Intent(AddViewScreen.this,
						ViewScreen.class);
				myIntent.putExtras(bundle);
				startActivityForResult(myIntent, 0);

			}
		});

	}

	public void btnAddJewellery(View v) {
		// date.setText(new Date().toString());
		Intent myIntent = new Intent(AddViewScreen.this, AddScreen.class);
		startActivity(myIntent);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_view_screen, menu);
		return true;
	}

}
