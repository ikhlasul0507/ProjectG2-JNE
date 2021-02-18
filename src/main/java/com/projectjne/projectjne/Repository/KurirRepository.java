package com.projectjne.projectjne.Repository;

import com.projectjne.projectjne.Model.DaftarHarga;
import com.projectjne.projectjne.Model.Kurir;

import java.util.List;

public interface KurirRepository {
    Kurir findById(String idKurir);
    List<Kurir> findByName(String namaKurir);
    List<Kurir> findAll();
    void saveKurir(Kurir kurir);
    void deleteKurirById(String idKurir);
    void updateKurir(Kurir kurir);
    List<Kurir> findWithPaging(int page, int limit);
}
