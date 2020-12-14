package no.hiof.larsmra.walkroutegenerator.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.Arrays;

import no.hiof.larsmra.walkroutegenerator.R;
import no.hiof.larsmra.walkroutegenerator.models.Schedule;

public class CreateScheduleActivity extends AppCompatActivity {

    private static final String TAG = "CreateScheduleActivity";

    private boolean[] days = new boolean[7];

    private EditText etTitle;
    private EditText etTime;
    private ToggleButton tbMon;
    private ToggleButton tbTue;
    private ToggleButton tbWed;
    private ToggleButton tbThu;
    private ToggleButton tbFri;
    private ToggleButton tbSat;
    private ToggleButton tbSun;

    private Button cancelBtn;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule);

        etTitle = findViewById(R.id.etTitle);
        etTime = findViewById(R.id.etTime);
        tbMon = findViewById(R.id.tbMon);
        tbTue = findViewById(R.id.tbTue);
        tbWed = findViewById(R.id.tbWed);
        tbThu = findViewById(R.id.tbThu);
        tbFri = findViewById(R.id.tbFri);
        tbSat = findViewById(R.id.tbSat);
        tbSun = findViewById(R.id.tbSun);

        cancelBtn = findViewById(R.id.cancel_schedule);
        saveBtn = findViewById(R.id.save_schedule);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked: cancel button");
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked");

                days[0] = tbMon.isChecked();
                days[1] = tbTue.isChecked();
                days[2] = tbWed.isChecked();
                days[3] = tbThu.isChecked();
                days[4] = tbFri.isChecked();
                days[5] = tbSat.isChecked();
                days[6] = tbSun.isChecked();

                /*
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse(etTime.getText().toString());
                }
                catch (ParseException pe) {
                    pe.printStackTrace();
                }
                */

                Schedule schedule = new Schedule(etTitle.getText().toString(),
                        etTime.getText().toString(), days);

                Log.d(TAG, schedule.getTitle() + " " + schedule.getTime() +
                        " " + Arrays.toString(schedule.getDays()));



                finish();
            }
        });
    }
}