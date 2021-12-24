package id.dwiweka.temanngopi.database.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import id.dwiweka.temanngopi.database.entitas.Akun;

@Dao
public interface AkunDao {
    @Query("SELECT * FROM Akun")
    List<Akun>getAll();

    @Query("INSERT INTO Akun (nama_lengkap,gender,no_telp,usia,favorite,email,password) VALUES(:nama_lengkap,:gender,:no_telp,:usia,:favorite,:email,:password)")
    void insertAll(String nama_lengkap,String gender, String no_telp, int usia, String favorite, String email, String password);

    @Query("UPDATE Akun SET nama_lengkap=:nama_lengkap, gender=:gender, no_telp=:no_telp, usia=:usia, favorite=:favorite, email=:email, password=:password WHERE idAkun=:idAkun")
    void update(int idAkun, String nama_lengkap, String gender, String no_telp, int usia,String favorite,String email, String password);

    @Query("SELECT * FROM Akun WHERE idAkun=:idAkun")
    Akun get(int idAkun);

    @Delete
    void delete(Akun... akun);
}
