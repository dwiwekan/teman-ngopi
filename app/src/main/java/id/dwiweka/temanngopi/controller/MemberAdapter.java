package id.dwiweka.temanngopi.controller;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.dwiweka.temanngopi.R;
import id.dwiweka.temanngopi.database.entitas.Akun;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder>{

    private Context c;
    private List<Akun> akuns;
    private Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public MemberAdapter(Context c,List<Akun> akun){
        this.c = c;
        this.akuns = akun;
    }

    @NonNull
    @Override
    public MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member,null);

        return new MemberHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberHolder holder, int position) {
        holder.namaLengkap.setText(akuns.get(position).getNama_lengkap());
        holder.gender.setText(akuns.get(position).getGender());
        holder.noTelp.setText(akuns.get(position).getNo_telp());
        holder.usia.setText(String.valueOf(akuns.get(position).getUsia()));
        holder.favorit.setText(akuns.get(position).getNo_telp());
        holder.email.setText(akuns.get(position).getEmail());
        holder.pass.setText(akuns.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return akuns.size();
    }

    public class MemberHolder extends RecyclerView.ViewHolder {
        TextView namaLengkap,gender,noTelp,usia,favorit,email,pass;

        public MemberHolder(@NonNull View itemView) {
            super(itemView);
            this.namaLengkap = itemView.findViewById(R.id.nameMember);
            this.gender = itemView.findViewById(R.id.genderMember);
            this.noTelp = itemView.findViewById(R.id.noTelpMember);
            this.usia = itemView.findViewById(R.id.usiaMember);
            this.favorit = itemView.findViewById(R.id.favoritMember);
            this.email = itemView.findViewById(R.id.emailMember);
            this.pass = itemView.findViewById(R.id.passMember);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}
