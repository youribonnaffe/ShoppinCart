package com.github.shoppincart.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	private static final String ITEM_TABLE_NAME = "item";
	private static final String ITEM_TABLE_CREATE = "CREATE TABLE " + ITEM_TABLE_NAME + " (" + "id"
			+ " INTEGER PRIMARY KEY, " + "label" + " TEXT);";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(ITEM_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
