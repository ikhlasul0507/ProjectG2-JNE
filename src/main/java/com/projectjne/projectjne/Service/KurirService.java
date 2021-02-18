package com.projectjne.projectjne.Service;

import com.projectjne.projectjne.Model.DaftarHarga;
import com.projectjne.projectjne.Model.Kurir;

import java.util.List;

public interface KurirService {
    Kurir findById(String idKurir);
    Kurir findByName(String namaKurir);
    List<Kurir> findAll();
    void saveKurir(Kurir kurir);
    void deleteKurirById(String idKurir);
    void updateKurir(Kurir kurir);
    boolean isKurirExist(Kurir kurir);
    List<Kurir> findWithPaging(int page, int limit);
}
