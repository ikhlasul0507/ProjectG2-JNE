package com.projectjne.projectjne.Service;

import com.projectjne.projectjne.Model.Jadwal;

import java.util.List;

public interface JadwalService {
    Jadwal findById(String idJadwal);
    Jadwal findByIdKurir(String idKurir);
    Jadwal findByIdPaket(String idPaket);
    List<Jadwal> findAll();
    void saveJadwal(Jadwal jadwal);
    void deleteJadwalById(String idJadwal);
    void updateJadwal(Jadwal jadwal);
    boolean isJadwalExist(Jadwal jadwal);
    List<Jadwal> findWithPaging(int page, int limit);
}
