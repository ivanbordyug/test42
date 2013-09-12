package Managers;

import com.coffeecups.testproject.LoginActivity;
import com.coffeecups.testproject.R;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TestsManager {
	Context context;

	public TestsManager(Context context) {
		this.context = context;
	}

	public void runTestUP() {
		DBManager DbManager = new DBManager(context);
		ContentValues cv = new ContentValues();
		testAddingToDatabase(DbManager, cv);
		testReadingFromDB(DbManager);
		testUpdatingUserInfo(DbManager);

	}

	private void testAddingToDatabase(DBManager DbManager, ContentValues cv) {
		cv.put("name", "Test");
		try {
			DbManager.insert("users", null, cv);
		} catch (Exception e) {
			Toast.makeText(context, "Test adding to db failed", 1000).show();
		}
	}

	private void testReadingFromDB(DBManager DbManager) {
		try {
			DbManager.select("usersinfo", "userId = ?", new String[] { "1" });
		} catch (Exception e) {
			Toast.makeText(context, "Test reading from db failed", 1000).show();
		}
	}

	private void testUpdatingUserInfo(DBManager DbManager) {

		try {
			Cursor cursor = DbManager.select("usersinfo", "id = ?",
					new String[] { "1" });
			if (cursor.moveToFirst()) {
				ContentValues cv = new ContentValues();
				cv.put("about",
						cursor.getString(cursor.getColumnIndex("about")));
				DbManager.update("usersinfo", cv, "id = ?",
						new String[] { "1" });
				cursor.close();
			}

		} catch (Exception e) {
			Toast.makeText(context, "Test updating user failed", 1000).show();
		}
	}

	public void runTestLogin() {
		if (!isOnline()) {
			Toast.makeText(context, R.string.enableInternetMsg, 5000).show();
		}
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	public boolean checkInfo(String name, String surname, String dob,
			String uid, DBManager manager) {
		Cursor cursor = manager.select("usersinfo", "userId = ?",
				new String[] { uid });
		if (cursor.moveToFirst()) {
			try {
				if (!cursor.getString(cursor.getColumnIndex("name")).trim()
						.equals(name)) {
					cursor.close();
					return false;
				}
				if (!cursor.getString(cursor.getColumnIndex("surname")).trim()
						.equals(surname)) {
					cursor.close();
					return false;
				}

				if (!cursor.getString(cursor.getColumnIndex("dob")).toString()
						.equals(dob)) {
					cursor.close();
					return false;
				}
			} catch (Exception e) {
				Toast.makeText(context, "Users data isn't setted", 1000).show();
			}
			cursor.close();
			return true;
		} else {
			cursor.close();
			return false;
		}
	}
}
