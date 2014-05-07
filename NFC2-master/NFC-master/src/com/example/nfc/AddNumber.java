package com.example.nfc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class AddNumber extends Activity
{

	EditText etName, etPhone;
	Button btnsubmit;
	ListView lvCustomList;
	
	@Override
	public void onCreate(Bundle savedState)
	{
		super.onCreate(savedState);
//		setContentView(R.layout.add_number);
		dialogs();
	
	}
	
	public void dialogs()
	{
		  final Dialog dialog = new Dialog(AddNumber.this);
          // Include dialog.xml file
          dialog.setContentView(R.layout.add_number);
          // Set dialog title
          dialog.setTitle("Add Phone Number");

          // set values for custom dialog components - text, image and button
          etPhone = (EditText) dialog.findViewById(R.id.et_phone);
          etName = (EditText) dialog.findViewById(R.id.et_name);

          dialog.show();
           
          Button btnSet = (Button) dialog.findViewById(R.id.btn_submit);
          btnSet.setOnClickListener(new OnClickListener() 
          {
              @Override
              public void onClick(View v)
              {
            	  String phoneNumber = etPhone.getText().toString();
            	  String phoneText = etName.getText().toString();
            	  
            	  Log.d("MainActivity", "phone:"+phoneNumber);
            	  Log.d("MainActivity", "text:"+phoneText);
            	  
            	  startActivity(new Intent(AddNumber.this, SendList.class));
            	  dialog.dismiss();
            	  finish();
            	  
            	  String name = phoneText;
          		  String phoneNo = phoneNumber;
          
          		  String query = "INSERT INTO PHONE_CONTACTS(name,phone) values ('"
          				+ name + "','" + phoneNo + "')";
          		  SendList.sqlHandler.executeQuery(query);
            	  
            	  MainActivity.countNumbers++;
            	  Log.d("MainActivity", "countNumbers:"+MainActivity.countNumbers);
              }
          }); 
         
	}
	
}
