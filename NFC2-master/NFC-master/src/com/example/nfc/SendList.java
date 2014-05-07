package com.example.nfc;

import java.util.ArrayList;

import com.example.nfc.R;

import SqliteDB.ContactListAdapter;
import SqliteDB.ContactListItems;
import SqliteDB.SqlHandler;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SendList extends Activity {

	static SqlHandler sqlHandler;
	ListView lvCustomList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		lvCustomList = (ListView) findViewById(R.id.lv_custom_list);
		sqlHandler = new SqlHandler(this);
		showList();
		
//		String name = "as";
//		String phoneNo = "dsd";
//
//		String query = "INSERT INTO PHONE_CONTACTS(name,phone) values ('"
//				+ name + "','" + phoneNo + "')";
//		sqlHandler.executeQuery(query);
		
//		showList();
		
	}

	void showList() {

		ArrayList<ContactListItems> contactList = new ArrayList<ContactListItems>();
		contactList.clear();
		String query = "SELECT * FROM PHONE_CONTACTS ";
		Cursor c1 = sqlHandler.selectQuery(query);
		if (c1 != null && c1.getCount() != 0) {
			if (c1.moveToFirst()) {
				do {
					ContactListItems contactListItems = new ContactListItems();

					contactListItems.setSlno(c1.getString(c1
							.getColumnIndex("slno")));
					contactListItems.setName(c1.getString(c1
							.getColumnIndex("name")));
					contactListItems.setPhone(c1.getString(c1
							.getColumnIndex("phone")));
					contactList.add(contactListItems);

				} while (c1.moveToNext());
			}
		}
		c1.close();

		ContactListAdapter contactListAdapter = new ContactListAdapter(
				SendList.this, contactList);
		lvCustomList.setAdapter(contactListAdapter);

		lvCustomList.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) 
			{
				
				if(MainActivity.listClickEnable==true)
				{

					Object o = lvCustomList.getItemAtPosition(position);
					ContactListItems ci = (ContactListItems) o;

					Toast.makeText(getBaseContext(), "pos:" + position + " " + ci,
							Toast.LENGTH_SHORT).show();
					
					MainActivity.phoneNumber =ci.getPhone();
	    			MainActivity.listClickEnable = false;
	    			startActivity(new Intent(getBaseContext(), SendNFC.class));
	    			finish();
				}
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}