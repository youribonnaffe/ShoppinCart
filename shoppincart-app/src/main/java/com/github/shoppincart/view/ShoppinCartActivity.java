package com.github.shoppincart.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;

public class ShoppinCartActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final AutoCompleteTextView newItemTextView = (AutoCompleteTextView) findViewById(R.id.new_item);
		String[] items = getResources().getStringArray(R.array.items_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.autocomplete_list, items);
		newItemTextView.setAdapter(adapter);

		ExpandableListView itemsListView = (ExpandableListView) findViewById(R.id.list_items);
		itemsListView.setItemsCanFocus(false);
		itemsListView.setChoiceMode(ExpandableListView.CHOICE_MODE_MULTIPLE);

		itemsListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				strikeThroughListItem(v);
				return false;
			}
		});

		final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_items,
				R.id.list_item_textview);
		List<Map<String, ?>> groupData = new ArrayList<Map<String, ?>>();
		Map<String, String> group1 = new HashMap<String, String>();
		group1.put("id", "0");
		group1.put("label", "group1");
		groupData.add(group1);
		Map<String, String> group2 = new HashMap<String, String>();
		group2.put("id", "1");
		group2.put("label", "group2");
		groupData.add(group1);

		List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
		List<Map<String, String>> childOfGroup1 = new ArrayList<Map<String, String>>();
		Map<String, String> child1OfGroup1 = new HashMap<String, String>();
		child1OfGroup1.put("label", "label1");
		childOfGroup1.add(child1OfGroup1);
		Map<String, String> child2OfGroup1 = new HashMap<String, String>();
		child2OfGroup1.put("label", "label2");
		childOfGroup1.add(child2OfGroup1);
		childData.add(childOfGroup1);

		ExpandableListAdapter listAdapter = new SimpleExpandableListAdapter(this, groupData,
				android.R.layout.simple_expandable_list_item_1, new String[] { "label" },
				new int[] { android.R.id.text1 }, childData, R.layout.list_items, new String[] { "label" },
				new int[] { R.id.list_item_textview });
		itemsListView.setAdapter(listAdapter);

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
		if (item.length() != 0) {
			arrayAdapter.add(item);
			newItemTextView.setText("");
		}
	}

	private void strikeThroughListItem(View view) {
		CheckedTextView checkedTextView = (CheckedTextView) view;
		checkedTextView.toggle();
		if (checkedTextView.isChecked()) {
			checkedTextView.setPaintFlags(checkedTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
		} else {
			checkedTextView.setPaintFlags(checkedTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}
	}
}
