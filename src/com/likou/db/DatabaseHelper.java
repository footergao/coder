package com.likou.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.likou.util.LogUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String TAG = DatabaseHelper.class.getName();

	private static final String DB_NAME = "gl.db";
	private static final int DB_VERSION = 3;

	// 数据库文件目标存放路径为系统默认位置，com.likou 是你的包名
	private static String DB_PATH = "/data/data/com.likou/databases/";

	// private Dao<HttpCache, String> mHttpCache = null;
	// private Dao<Product, Integer> mProduct = null;
	// private Dao<Shop, Integer> mShop = null;

	private Context mContext;

	public DatabaseHelper(Context context) {
		this(context, DB_NAME, null, DB_VERSION);
		this.mContext = context;
	}

	public DatabaseHelper(Context context, String databaseName,
			CursorFactory factory, int databaseVersion) {
		super(context, databaseName, factory, databaseVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
//		 TableUtils.createTable(arg1, HttpCache.class);
		// TableUtils.createTable(arg1, Product.class);
		// TableUtils.createTable(arg1, Shop.class);
		LogUtils.LOGD(TAG, "Create Table Success");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		// TableUtils.dropTable(arg1, HttpCache.class, true);
		// TableUtils.dropTable(arg1, Product.class, true);
		// TableUtils.dropTable(arg1, Shop.class, true);
		onCreate(arg0, arg1);
	}

	public void copyDataBase() {
		try {
			// Open your local db as the input stream
			InputStream myInput = mContext.getAssets().open(DB_NAME);
			// Path to the just created empty db
			String outFileName = DB_PATH + DB_NAME;
			// Open the empty db as the output stream
			OutputStream myOutput = new FileOutputStream(outFileName);
			// transfer bytes from the inputfile to the outputfile
			byte[] buffer = new byte[1024];
			int length;
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myOutput.flush();
			// Close the streams
			myOutput.close();
			myInput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
