package com.github.shoppincart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;

public class ShoppinCartActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.new_item);
		String[] items = getResources().getStringArray(R.array.items_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.autocomplete_list, items);
		textView.setAdapter(adapter);

		ListView listView = (ListView) findViewById(R.id.list_items);
		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_items,
				R.id.list_item_textview);
		listView.setAdapter(arrayAdapter);

		Button addButton = (Button) findViewById(R.id.add_item);
		addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addNewItemToList(textView, arrayAdapter);
			}
		});
	}

	private void addNewItemToList(final AutoCompleteTextView textView, final ArrayAdapter<String> arrayAdapter) {
		String item = textView.getText().toString();
		arrayAdapter.add(item);
		textView.setText("");
	}
}
