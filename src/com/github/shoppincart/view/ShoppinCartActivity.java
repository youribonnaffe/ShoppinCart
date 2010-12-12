package com.github.shoppincart.view;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

public class ShoppinCartActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final AutoCompleteTextView newItemTextView = (AutoCompleteTextView) findViewById(R.id.new_item);
		String[] items = getResources().getStringArray(R.array.items_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.autocomplete_list, items);
		newItemTextView.setAdapter(adapter);

		ListView itemsListView = (ListView) findViewById(R.id.list_items);
		itemsListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				strikeThroughListItem(view);
			}

		});

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_items,
				R.id.list_item_textview);
		itemsListView.setAdapter(arrayAdapter);

		Button addButton = (Button) findViewById(R.id.add_item);
		addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addNewItemToList(newItemTextView, arrayAdapter);
			}
		});
	}

	private void addNewItemToList(final AutoCompleteTextView newItemTextView, final ArrayAdapter<String> arrayAdapter) {
		String item = newItemTextView.getText().toString();
		arrayAdapter.add(item);
		newItemTextView.setText("");
	}

	private void strikeThroughListItem(View view) {
		CheckedTextView checkedTextView = (CheckedTextView) view;
		if (checkedTextView.isChecked()) {
			checkedTextView.setPaintFlags(checkedTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
		} else {
			checkedTextView.setPaintFlags(checkedTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}
	}
}
