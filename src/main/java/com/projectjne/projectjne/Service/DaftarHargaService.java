package com.projectjne.projectjne.Service;

import com.projectjne.projectjne.Model.DaftarHarga;

import java.util.List;

public interface DaftarHargaService {
    DaftarHarga findById(String idDaftarHarga);
    DaftarHarga findByName(String namaPaket);
    List<DaftarHarga> findAll();
    void saveDaftarHarga(DaftarHarga daftarHarga);
    void deleteDaftarHargaById(String idDaftarHarga);
    void updateDaftarHarga(DaftarHarga daftarHarga);
    boolean isDaftarHargaExist(DaftarHarga daftarHarga);
    List<DaftarHarga> findWithPaging(int page, int limit);
}
