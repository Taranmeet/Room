package com.ttn.bootcamp.room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.ttn.bootcamp.room.database.Courses;
import com.ttn.bootcamp.room.database.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.b_add_course).setOnClickListener(this);
        findViewById(R.id.b_get_course).setOnClickListener(this);
        findViewById(R.id.b_add_student).setOnClickListener(this);
        findViewById(R.id.b_get_student).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b_add_course:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        Courses courses = new Courses();
                        courses.setCourseName("English");
                        RoomApplication.mDatabase.coursesDao().insertAll(courses);
                        return null;
                    }
                }.execute();

                break;

            case R.id.b_get_course:
                new AsyncTask<Void, Void, Integer>() {

                    @Override
                    protected Integer doInBackground(Void... voids) {
                        int courseCount = RoomApplication.mDatabase.coursesDao().getAll().size();
                        return new Integer(courseCount);
                    }

                    @Override
                    protected void onPostExecute(Integer integer) {
                        super.onPostExecute(integer);
                        String text = "Fetched Courses Count " + integer.intValue();
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                }.execute();

                break;

            case R.id.b_add_student:
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... voids) {
                        Student student = new Student();
                        student.setFirstName("Taranmeet");
                        student.setLastName("Singh");
                        student.setRollNumber(1);
                        student.setId(RoomApplication.mDatabase.coursesDao().getAll().get(0).getId());

                        RoomApplication.mDatabase.studentDao().insertAll(student);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);

                        Toast.makeText(MainActivity.this, "Student Added", Toast.LENGTH_SHORT).show();
                    }
                }.execute();
                break;

            case R.id.b_get_student:

                new AsyncTask<Void, Void, Student>() {

                    @Override
                    protected Student doInBackground(Void... voids) {
                        return RoomApplication.mDatabase.studentDao().getAll().get(0);
                    }

                    @Override
                    protected void onPostExecute(Student student) {
                        super.onPostExecute(student);
                        String text = "Top Student is " + student.getFirstName() + " " + student.getLastName();
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                }.execute();

                break;
        }
    }
}
