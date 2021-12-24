package id.dwiweka.temanngopi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    public Button register,login;
    public TextInputLayout email,password;
    public String emailUser,passwordUser;
    public int loginAdmin,checkValid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //========Button========
        register = (Button)findViewById(R.id.loginRegister);
        register.setOnClickListener(clickListener);

        login = (Button)findViewById(R.id.loginButton);
        login.setOnClickListener(clickListener);

        //=========Input User=======
        email = (TextInputLayout)findViewById(R.id.email);
        password = (TextInputLayout)findViewById(R.id.password);



    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.loginRegister:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    break;
                case R.id.loginButton:
                    checkValid = 0;
                    checkEmail();
                    checkPass();
                    loginAdmin +=1;
                    if(loginAdmin <= 3){
                        Toast.makeText(LoginActivity.this, "Klik login 3 kali",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginActivity.this, "Selamat Datang Admin",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplication(),AdminDashboardActivity.class));
                    }


            }
        }
    };



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


}
