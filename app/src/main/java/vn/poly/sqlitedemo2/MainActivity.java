package vn.poly.sqlitedemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StudentReaderSql studentReaderSql;

    private ListView lvList;

    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvList = findViewById(R.id.lvList);

        studentReaderSql = new StudentReaderSql(MainActivity.this);

        Student student = new Student();
        student.id = "PH" + System.currentTimeMillis();
        student.name = "Nguyen " + System.currentTimeMillis();

        long result = studentReaderSql.insertStudent(student);


        if (result > 0) {
            // thanh cong
        } else {
            // that bai
        }


        List<Student> studentList = studentReaderSql.getAllStudents();


        studentAdapter = new StudentAdapter(this, studentList);
        lvList.setAdapter(studentAdapter);

        Log.e("SIZE", studentList.size() + "");

        // long currentTime = System.currentTimeMillis();
    }


}
