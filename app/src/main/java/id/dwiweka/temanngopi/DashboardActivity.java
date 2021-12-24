package id.dwiweka.temanngopi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DashboardActivity extends AppCompatActivity {

    public String namaAkun,genderAkun,genderAkunSebutan;
    public TextView namaAkunView,genderAkunView,tanggalView;
    public Button logout;

    //===Date===//
    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat1;
    private String date;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //============ Get Akun Data From Register =============//
        Intent userIntent = getIntent();
        namaAkun = userIntent.getStringExtra(MainActivity.EXTRA_MESSAGE_NAMA);
        genderAkun = userIntent.getStringExtra(MainActivity.EXTRA_MESSAGE_GENDER);
        Toast.makeText(DashboardActivity.this,genderAkun,Toast.LENGTH_SHORT).show();

        namaAkunView = (TextView)findViewById(R.id.usernameView);
        namaAkunView.setText(namaAkun);

        if(genderAkun.equals("Lelaki")){
            genderAkunSebutan="Mas";
        }else{
            genderAkunSebutan="Mbak";
        }
        genderAkunView = (TextView)findViewById(R.id.info_text);
        genderAkunView.setText("Selamat Datang "+genderAkunSebutan);

        //====Tanggal Info====//
        tanggalView = (TextView)findViewById(R.id.tanggalInfo);
        calendar = Calendar.getInstance();
        dateFormat1 = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat1.format(calendar.getTime());
        tanggalView.setText(date);

        //======Button=======//
        logout = (Button)findViewById(R.id.buttonLogout);
        logout.setOnClickListener(clickListener);

    }
    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.buttonLogout:
                    onDestroy();
            }
        }
    };

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}