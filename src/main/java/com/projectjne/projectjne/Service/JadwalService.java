package com.projectjne.projectjne.Service;

import com.projectjne.projectjne.Model.Jadwal;

import java.util.List;

public interface JadwalService {
    Jadwal findById(String idjadwal);
    List<Jadwal> findAll();
    List<Jadwal> find();
    void saveJadwal(Jadwal jadwal);
    void deleteJadwalById(String idJadwal);
    void updateJadwal(Jadwal jadwal);
}
