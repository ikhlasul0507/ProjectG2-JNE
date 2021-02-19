package com.projectjne.projectjne.Repository;

import com.projectjne.projectjne.Model.Kendaraan;

import java.util.List;

public interface KendaraanRepository {
    Kendaraan findById(String idKendaraan);
    List<Kendaraan> findByName(String platKendaraan);
    List<Kendaraan> findAll();
    void saveKendaraan(Kendaraan kendaraan);
    void deleteKendaraanById(String idKendaraan);
    void updateKendaraan(Kendaraan kendaraan);
    List<Kendaraan> findWithPaging(int page, int limit);
}
