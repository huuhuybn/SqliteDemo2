package vn.poly.sqlitedemo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StudentReaderSql extends SQLiteOpenHelper {


    // ten bang?
    public static final String TABLE_NAME = "Student";

    // cot id
    public static final String COL_ID = "id";

    // cot name
    public static final String COL_NAME = "name";


    public StudentReaderSql(Context context) {
        super(context, "students.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // cau lenh tao bang o day !!!

        String create_table = "CREATE TABLE " + TABLE_NAME +
                " (" + COL_ID + " TEXT PRIMARY KEY," + COL_NAME + " TEXT)";

        Log.e("CL", create_table);

        // thuc thi

        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // ko ghi gi ca!


    }


    public long insertStudent(Student student) {

        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, student.id);
        contentValues.put(COL_NAME, student.name);

        // cau lenh them vao cs du lieu

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);


        sqLiteDatabase.close();
        return result;

    }


    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        // xin quyen
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String truyVan = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(truyVan, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.id = cursor.getString(cursor.getColumnIndex(COL_ID));
                student.name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                studentList.add(student);

            } while (cursor.moveToNext());


            cursor.close();

        }

        sqLiteDatabase.close();

        return studentList;
    }

    public void delStudent(String id) {

        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();


        sqLiteDatabase.delete(TABLE_NAME, COL_ID + "=?", new String[]{id});

        sqLiteDatabase.close();

    }

    public long updateStudent(Student student){

        // xin quyen
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();


        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID, student.id);
        contentValues.put(COL_NAME, student.name);

        // cau lenh them vao cs du lieu

        long result = sqLiteDatabase.update(TABLE_NAME,contentValues,
                COL_ID + "=?", new String[]{student.id});


        sqLiteDatabase.close();
        return result;
    }
}
