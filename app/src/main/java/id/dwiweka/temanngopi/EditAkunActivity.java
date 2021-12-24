package id.dwiweka.temanngopi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputLayout;

import id.dwiweka.temanngopi.database.AppDatabase;
import id.dwiweka.temanngopi.database.entitas.Akun;

public class EditAkunActivity extends AppCompatActivity {

    public Button tombolDaftar;
    public TextInputLayout namaLengkap,noTelp,email,password;
    public RadioGroup radioGroup;
    public RadioButton selectedRadioButton;

    public Slider slider;
    public CheckBox americanoCheck,kopi_susuCheck,capucinoCheck,kopi_rumCheck,mocachinoCheck;
    public int usiaUser,checkValid;
    public String namaUser,noTelpUser,emailUser,passwordUser,genderUser,favorite;

    private AppDatabase database;
    private int idAkun = 0;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        //============ Button ===========//
        tombolDaftar = (Button)findViewById(R.id.buttonDaftar);
        tombolDaftar.setOnClickListener(clickListener);
        //============ Radio ===========//
        radioGroup = findViewById(R.id.radioGroup);
        //========== TextInputLayout ========== //
        namaLengkap = (TextInputLayout)findViewById(R.id.namalengkap);
        noTelp = (TextInputLayout)findViewById(R.id.nope);
        email = (TextInputLayout)findViewById(R.id.email);
        password = (TextInputLayout)findViewById(R.id.password);
        // ========= Database ============ //
        database = AppDatabase.getInstance(getApplicationContext());
        // ========== Radio Button =========== //
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        // ========= Slider ======
        slider = findViewById(R.id.umur);
        //========= CheckBox ===========//
        americanoCheck = findViewById(R.id.americano);
        kopi_susuCheck = findViewById(R.id.kopi_susu);
        capucinoCheck = findViewById(R.id.cappuicno);
        kopi_rumCheck = findViewById(R.id.kopi_rum);
        mocachinoCheck = findViewById(R.id.mocahino);


        Intent intent = getIntent();
        idAkun = intent.getIntExtra("idAkun",0);
        if(idAkun>0){
            isEdit = true;
            Akun akun = database.akunDao().get(intent.getIntExtra("idAkun",0));
            namaLengkap.getEditText().setText(akun.nama_lengkap);
            noTelp.getEditText().setText(akun.no_telp);
            email.getEditText().setText(akun.email);
            password.getEditText().setText(akun.password);
            if(akun.gender.equals("Lelaki")){
                radioGroup.check(R.id.radio_button_1);
            }else{
                radioGroup.check(R.id.radio_button_2);
            }
            slider.setValue(akun.usia);
            usiaUser = akun.usia;
        }else{
            isEdit = false;
            usiaUser = 17;
        }
    }


    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.buttonDaftar:
                    checkValid = 0;
                    checkNama();
                    checkEmail();
                    checkPass();
                    checkNope();
//                    Toast.makeText(MainActivity.this, "Selamat Datang Sobat",Toast.LENGTH_SHORT).show();
                    if(checkValid==0) {
                        btnAlertDialog();
                    }
                    break;


            }
        }
    };




    private void btnAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Register Akun");

        // ========= Get Value ============ //
        namaUser = namaLengkap.getEditText().getText().toString();

        slider.addOnChangeListener(new Slider.OnChangeListener(){
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                usiaUser = (int) value;
            }
        });

        genderUser = "Lelaki";
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1){
            selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedRbText = selectedRadioButton.getText().toString();
            if(selectedRbText.equals("Mas")){
                genderUser = "Lelaki";
            }else{
                genderUser = "Perempuan";
            }
        }

        noTelpUser = noTelp.getEditText().getText().toString();
        emailUser = email.getEditText().getText().toString();
        passwordUser = password.getEditText().getText().toString();

        favorite = "";
        if(americanoCheck.isChecked()){
            favorite += americanoCheck.getText().toString() + ", ";
        }
        if(kopi_susuCheck.isChecked()){
            favorite += kopi_susuCheck.getText().toString() + ", ";
        }
        if(capucinoCheck.isChecked()){
            favorite += capucinoCheck.getText().toString() + ", ";
        }
        if(kopi_rumCheck.isChecked()){
            favorite += kopi_rumCheck.getText().toString() + ", ";
        }
        if(mocachinoCheck.isChecked()){
            favorite += mocachinoCheck.getText().toString() + ", ";
        }


        // ============= End Get Value ============== //

        alert.setMessage("Nama : " + namaUser +
                "\nGender : " + genderUser +
                "\nNomor Telpon : " + noTelpUser +
                "\nUsia : " + usiaUser +
                "\nFavorite : " + favorite +
                "\nEmail : " + emailUser +
                "\nPassword : " + passwordUser);


        alert.setPositiveButton("Edit Akun", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Toast.makeText(EditAkunActivity.this,"Berhasil Edit",Toast.LENGTH_SHORT).show();
                database.akunDao().update(idAkun,
                        namaUser,
                        genderUser,
                        noTelpUser,
                        usiaUser,
                        favorite,
                        emailUser,
                        passwordUser);
                finish();
                updateAccount();
            }
        });

        alert.setNegativeButton("Batal",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                Toast.makeText(EditAkunActivity.this,"Gagal Mendaftar",Toast.LENGTH_SHORT).show();

            }
        });

        AlertDialog message = alert.create();
        message.show();

    }

    public void checkNama(){
        namaUser = namaLengkap.getEditText().getText().toString();
        if(TextUtils.isEmpty(namaUser)){
            namaLengkap.setError("Masukkan Nama Lengkap!");
            checkValid +=1;
        }
    }
    public void checkEmail(){
        emailUser = email.getEditText().getText().toString();
        if(TextUtils.isEmpty(emailUser)){
            email.setError("Masukkan Email!");
            checkValid +=1;
        }
    }
    public void checkPass(){
        passwordUser = password.getEditText().getText().toString();
        if(TextUtils.isEmpty(passwordUser)){
            password.setError("Masukkan Password!");
            checkValid +=1;
        }
    }
    public void checkNope(){
        noTelpUser = noTelp.getEditText().getText().toString();
        if(TextUtils.isEmpty(noTelpUser)){
            noTelp.setError("Masukkan Alamat!");
            checkValid +=1;
        }
    }

    public static final String EXTRA_MESSAGE_NAMA = "namaUserRegister";
    public static final String EXTRA_MESSAGE_GENDER = "genderUserRegister";

    public void updateAccount(){
        Intent update = new Intent(this,AdminDashboardActivity.class);
        startActivity(update);

    };
}
