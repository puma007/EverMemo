package com.zhan_dui.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MemoDB extends SQLiteOpenHelper {

	public final static String ID = "_id";
	

	private final String CREATE_MEMO_TABLE = "create table Memo(`_id` integer primary key autoincrement,"
			+ "`content` text,"
			+ "`createdtime` text,"
			+ "`updatedtime` text,"
			+ "`hash` text,"
			+ "`order` int,"
			+ "`lastsynctime` text,"
			+ "`status` text,"
			+ "`guid` text,"
			+ "`enid` text,"
			+ "`wallid` text," + "`attributes` text);";

	public static final String Name = "EverMemo";
	public static final int VERSION = 1;

	public final static String MEMO_TABLE_NAME = "Memo";

	public MemoDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	public MemoDB(Context context) {
		this(context, Name, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_MEMO_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public long insertMemo(Memo memo) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("content", memo.getContent());
		contentValues.put("createdtime", memo.getCreatedTime());
		contentValues.put("updatedtime", memo.getUpdatedTime());
		contentValues.put("hash", memo.getHash());
		contentValues.put("lastsynctime", memo.getLastSyncTime());
		contentValues.put("status", memo.getStatus());
		contentValues.put("guid", memo.getGuid());
		contentValues.put("enid", memo.getEnid());
		contentValues.put("wallid", memo.getWallId());
		contentValues.put("attributes", memo.getAttributes());
		return getWritableDatabase().insert(MEMO_TABLE_NAME, null,
				contentValues);
	}

	public long removeMemo(Memo memo) {
		if (memo.getId() != 0) {
			ContentValues values = new ContentValues();
			values.put("status", Memo.STATUS_DELETE);
			return getWritableDatabase().update(MEMO_TABLE_NAME, values,
					"_id=?", new String[] { String.valueOf(memo.getId()) });
		} else {
			return 0;
		}
	}

	public Cursor getAllMemos() {
		return getReadableDatabase().query(MEMO_TABLE_NAME, null, "status!=?",
				new String[] { Memo.STATUS_DELETE }, null, null, null);
	}
}