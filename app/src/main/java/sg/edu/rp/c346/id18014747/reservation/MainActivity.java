package sg.edu.rp.c346.id18014747.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView tvName;
    EditText edName;
    TextView tvPhone;
    EditText edPhone;
    TextView tvPax;
    EditText edPax;
    CheckBox cbSmoke;
    TimePicker tp;
    DatePicker dp;
    Button btnProceed;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.textViewName);
        edName = findViewById(R.id.editName);
        tvPhone = findViewById(R.id.textViewPhone);
        edPhone = findViewById(R.id.editPhone);
        tvPax = findViewById(R.id.textViewPax);
        edPax = findViewById(R.id.editPax);
        cbSmoke = findViewById(R.id.checkBoxSmoke);
        tp = findViewById(R.id.timePicker);
        dp = findViewById(R.id.datePicker);
        btnProceed = findViewById(R.id.buttonProceed);
        btnClear = findViewById(R.id.buttonClear);

        // Set default oncreate
        tp.setCurrentHour(19); // 7pm
        tp.setCurrentMinute(30); // :30
        dp.updateDate(2020, 6, 1);

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edName.getText().toString();
                String pax = edPax.getText().toString();
                String phone = edPhone.getText().toString();

                if (name.isEmpty() || pax.isEmpty() || phone.isEmpty()) {
                    toastNotice("Empty fields", 0);
                    return;
                }
                if (edPhone.getText().toString().length() != 8) {
                    toastNotice("Phone number invalid, 8 digits", 0);
                    return;
                }
                toastNotice("Reservation has been made!" +
                        "\nName: "+name.trim()+"" +
                        "\nPax: "+pax.trim()+"" +
                        "\nPhone: "+phone.trim()+"" +
                        "\nSmoking Area: "+(cbSmoke.isChecked()?"Yes":"No")+"" +
                        "\nDate: "+dp.getDayOfMonth()+"/"+ dp.getMonth()+1 +"/"+dp.getYear()+"" +
                        "\nTime: "+tp.getCurrentHour()+":"+tp.getCurrentMinute(), 1);

            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPhone.setText("");
                edPax.setText("");
                edName.setText("");
                tp.setCurrentHour(19); // 7pm
                tp.setCurrentMinute(30); // :30
                dp.updateDate(2020, 6, 1);
            }
        });
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (tp.getCurrentHour() < 8) {
                    tp.setCurrentHour(8);
                    //return; // don't think i need return statement here
                } else if (tp.getCurrentHour() >= 21) {
                    tp.setCurrentHour(20);
                }
            }
        });
    }

    private void toastNotice(String str, int duration) {
        // duration 0 = short, 1 = long
        Toast.makeText(MainActivity.this, str, duration).show();
    }
}
