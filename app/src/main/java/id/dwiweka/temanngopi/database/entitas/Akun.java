package id.dwiweka.temanngopi.database.entitas;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Akun {
    @PrimaryKey (autoGenerate = true)
    public int idAkun;

    @ColumnInfo(name="nama_lengkap")
    public String nama_lengkap;

    @ColumnInfo(name = "gender")
    public String gender;

    @ColumnInfo(name = "no_telp")
    public String no_telp;

    @ColumnInfo(name = "usia")
    public int usia;

    @ColumnInfo(name = "favorite")
    public String favorite;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    public Akun(int idAkun, String nama_lengkap
            , String gender , String no_telp , int usia , String favorite
            , String email , String password) {
        this.idAkun = idAkun;
        this.nama_lengkap = nama_lengkap;
        this.gender = gender;
        this.no_telp = no_telp;
        this.usia = usia;
        this.favorite = favorite;
        this.email = email;
        this.password = password;
    }

    @Ignore
    public Akun() {
        this.nama_lengkap = nama_lengkap;
        this.gender = gender;
        this.no_telp = no_telp;
        this.usia = usia;
        this.favorite = favorite;
        this.email = email;
        this.password = password;
    }

    public int getIdAkun() {
        return idAkun;
    }

    public void setIdAkun(int idAkun) {
        this.idAkun = idAkun;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
