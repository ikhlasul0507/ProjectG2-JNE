package com.projectjne.projectjne.Repository;

import com.projectjne.projectjne.Model.DaftarHarga;

import java.util.List;

public interface DaftarHargaRepository {
    DaftarHarga findById(String idDaftarHarga);
    List<DaftarHarga> findByName(String namaPaket);
    List<DaftarHarga> findAll();
    void saveDaftarHarga(DaftarHarga daftarHarga);
    void deleteDaftarHargaById(String idDaftarHarga);
    void updateDaftarHarga(DaftarHarga daftarHarga);
    List<DaftarHarga> findWithPaging(int page, int limit);
}
