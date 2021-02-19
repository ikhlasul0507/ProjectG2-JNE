package com.projectjne.projectjne.Repository;

import com.projectjne.projectjne.Model.Jadwal;

import java.util.List;

public interface JadwalRepository {
    Jadwal findById(String idJadwal);
    List <Jadwal> findByIdKurir(String idKurir);
    List <Jadwal> findByIdPaket(String idPaket);
    List<Jadwal> findAll();
    void saveJadwal(Jadwal jadwal);
    void deleteJadwalById(String idJadwal);
    void updateJadwal(Jadwal jadwal);
    List<Jadwal> findWithPaging(int page, int limit);
}
