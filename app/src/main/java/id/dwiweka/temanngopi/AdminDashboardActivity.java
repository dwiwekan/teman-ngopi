package id.dwiweka.temanngopi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import id.dwiweka.temanngopi.controller.MemberAdapter;
import id.dwiweka.temanngopi.database.AppDatabase;
import id.dwiweka.temanngopi.database.entitas.Akun;

public class AdminDashboardActivity extends AppCompatActivity {

    private Button logout;
    RecyclerView mRecyclerView;
    AppDatabase database;
    MemberAdapter memberAdapter;
    List<Akun> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        //==========Button===========//
        logout = (Button)findViewById(R.id.buttonLogout);
        logout.setOnClickListener(clickListener);

        //========== Data ===========//
        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list = database.akunDao().getAll();
        memberAdapter = new MemberAdapter(getApplicationContext(),list);
        memberAdapter.setDialog(new MemberAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit","Hapus"};
                dialog = new AlertDialog.Builder(AdminDashboardActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch(which){
                            case 0:
                                //Update disini, pending dulu
                                Intent intent = new Intent(AdminDashboardActivity.this,EditAkunActivity.class);
                                intent.putExtra("idAkun",list.get(position).idAkun);
                                startActivity(intent);
                                break;
                            case 1:
                                Akun akun = list.get(position);
                                database.akunDao().delete(akun);
                                onStart();
                                break;
                        }

                    }
                });
                dialog.show();
            }
        });


        mRecyclerView = findViewById(R.id.memberRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(memberAdapter);
    }



    public View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v){
            switch(v.getId()){
                case R.id.buttonLogout:
                    onDestroy();
                    break;
            }
        }
    };

    @Override
    protected void onStart(){
        super.onStart();
        list.clear();
        list.addAll(database.akunDao().getAll());
        memberAdapter.notifyDataSetChanged();
    }
}
