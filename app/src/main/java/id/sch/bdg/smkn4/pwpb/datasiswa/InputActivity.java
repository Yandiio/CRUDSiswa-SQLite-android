package id.sch.bdg.smkn4.pwpb.datasiswa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;


import id.sch.bdg.smkn4.pwpb.datasiswa.DBHelper.DatabaseHelper;
import id.sch.bdg.smkn4.pwpb.datasiswa.Model.Student;

public class InputActivity extends AppCompatActivity {
    EditText edtNo, edtName, edtGender, edtBirth, edtAddress;
    LinearLayout btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        initialize();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(InputActivity.this);
                Student currentPerson = new Student();
                currentPerson.setNo(Integer.parseInt(edtNo.getText().toString()));
                currentPerson.setName(edtName.getText().toString());
                currentPerson.setJenkel(edtGender.getText().toString());
                currentPerson.setTgl_lahir(edtBirth.getText().toString());
                currentPerson.setAlamat(edtAddress.getText().toString());
                db.insert(currentPerson);
                Intent i = new Intent(InputActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void initialize() {
        edtNo = findViewById(R.id.edtNo);
        edtName = findViewById(R.id.edtNama);
        edtGender = findViewById(R.id.edtJenkel);
        edtBirth = findViewById(R.id.edtTglLahit);
        edtAddress = findViewById(R.id.edtAlamat);
        btnSave = findViewById(R.id.btnSimpanData);
    }
}
