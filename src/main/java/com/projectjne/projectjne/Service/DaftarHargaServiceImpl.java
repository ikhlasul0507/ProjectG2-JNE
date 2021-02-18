package com.projectjne.projectjne.Service;

import com.projectjne.projectjne.Model.DaftarHarga;
import com.projectjne.projectjne.Repository.DaftarHargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DaftarHargaService")
public class DaftarHargaServiceImpl implements DaftarHargaService {

    @Autowired
    DaftarHargaRepository daftarHargaRepository;

    public List<DaftarHarga> findAll() {
        List<DaftarHarga> daftarHargas = daftarHargaRepository.findAll();
        return daftarHargas;
    }

    public DaftarHarga findById(String idDaftarHarga) {
        DaftarHarga dh;
        try{
            dh = daftarHargaRepository.findById(idDaftarHarga);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            dh = null;
        }
        return dh;
    }
    public DaftarHarga findByName(String namaPaket) {
        DaftarHarga dh;
        try{
            dh = (DaftarHarga) daftarHargaRepository.findByName(namaPaket);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            dh = null;
        }
        return dh;
    }

    public void saveDaftarHarga(DaftarHarga daftarHarga) {
        synchronized (this) {
            daftarHargaRepository.saveDaftarHarga(daftarHarga);
        }
    }

    public void updateDaftarHarga(DaftarHarga daftarHarga) {
        synchronized (this) {
            daftarHargaRepository.updateDaftarHarga(daftarHarga);
        }
    }

    public void deleteDaftarHargaById(String idDaftarHarga) {
        synchronized (this) {
            daftarHargaRepository.deleteDaftarHargaById(idDaftarHarga);
        }
    }
    public boolean isDaftarHargaExist(DaftarHarga daftarHarga){
        return daftarHargaRepository.findByName(daftarHarga.getNamaPaket()).size() != 0;
    }
    @Override
    public List<DaftarHarga> findWithPaging(int page, int limit) {
        return daftarHargaRepository.findWithPaging(page, limit);
    }



}
