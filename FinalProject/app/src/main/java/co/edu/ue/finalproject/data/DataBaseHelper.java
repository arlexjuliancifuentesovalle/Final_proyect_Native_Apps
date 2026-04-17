package co.edu.ue.finalproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    // Constantes necesarias
    private static final String DATABASE_NAME = "ServiceBarber.db";
    private static final int DATABASE_VERSION = 1;

    // Constantes para la tabla de usuarios
    public static final String TABLE_USERS = "usuarios";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Constructor simplificado
    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla
        String createTable = "CREATE TABLE " + TABLE_USERS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT UNIQUE, " +
                COLUMN_PASSWORD  + " TEXT )" ;
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Si la tabla existe, la borramos y creamos de nuevo
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public Boolean insertUsers(String userName, String email, String password){
     SQLiteDatabase db = this.getWritableDatabase();
     android.content.ContentValues values = new android.content.ContentValues();

     values.put(COLUMN_USERNAME, userName);
     values.put(COLUMN_EMAIL, email);
     values.put(COLUMN_PASSWORD, password);
     long result = db.insert(TABLE_USERS, null, values);
        return result != -1;
    }

    public boolean loginUser(String email, String password){

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_USERS +
                " WHERE " + COLUMN_EMAIL + "=? AND " + COLUMN_PASSWORD + "=?";

        android.database.Cursor cursor = db.rawQuery(query, new String[]{email, password});

        if(cursor.getCount() > 0){
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

}