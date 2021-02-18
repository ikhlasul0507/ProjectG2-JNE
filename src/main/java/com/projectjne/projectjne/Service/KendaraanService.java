package com.projectjne.projectjne.Service;

import com.projectjne.projectjne.Model.Kendaraan;

import java.util.List;

public interface KendaraanService {
    Kendaraan findById(String idKendaraan);
    Kendaraan findByName(String platKendaraan);
    List<Kendaraan> findAll();
    List<Kendaraan> find();
    void saveKendaraan(Kendaraan kendaraan);
    void deleteKendaraanById(String idKendaraan);
    void updateJadwal(Kendaraan kendaraan);
}
