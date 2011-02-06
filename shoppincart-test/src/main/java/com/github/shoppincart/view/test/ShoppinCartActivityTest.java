package com.github.shoppincart.view.test;

import android.widget.ListView;

import com.github.shoppincart.view.R;
import com.github.shoppincart.view.ShoppinCartActivity;

public class ShoppinCartActivityTest extends android.test.ActivityInstrumentationTestCase2<ShoppinCartActivity> {

	public ShoppinCartActivityTest() {
		super(ShoppinCartActivity.class);
	}

	public void testItemListIsEmptyAtStartup() {
		ListView itemsListView = (ListView) this.getActivity().findViewById(R.id.list_items);
		assertTrue(itemsListView.getCount() == 0);
	}

}
