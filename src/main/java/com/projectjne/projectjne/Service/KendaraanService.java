package com.projectjne.projectjne.Service;

import com.projectjne.projectjne.Model.DaftarHarga;
import com.projectjne.projectjne.Model.Kendaraan;

import java.util.List;

public interface KendaraanService {
    Kendaraan findById(String idKendaraan);
    Kendaraan findByName(String platKendaraan);
    List<Kendaraan> findAll();
    void saveKendaraan(Kendaraan kendaraan);
    void deleteKendaraanById(String idKendaraan);
    void updateKendaraan(Kendaraan kendaraan);
    boolean isKendaraanExist(Kendaraan kendaraan);
    List<Kendaraan> findWithPaging(int page, int limit);
}
