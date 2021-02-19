package com.projectjne.projectjne.Service;

import com.projectjne.projectjne.Model.DaftarHarga;
import com.projectjne.projectjne.Model.Kendaraan;
import com.projectjne.projectjne.Repository.DaftarHargaRepository;
import com.projectjne.projectjne.Repository.KendaraanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("KendaraanService")
public class KendaraanServiceImpl implements KendaraanService {

    @Autowired
    KendaraanRepository kendaraanRepository;

    public List<Kendaraan> findAll() {
        List<Kendaraan> kendaraans = kendaraanRepository.findAll();
        return kendaraans;
    }

    public Kendaraan findById(String idKendaraan) {
        Kendaraan dh;
        try{
            dh = kendaraanRepository.findById(idKendaraan);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            dh = null;
        }
        return dh;
    }
    @Override
    public Kendaraan findByName(String name) {
        return (Kendaraan) kendaraanRepository.findByName(name).get(0);
    }

    public void saveKendaraan(Kendaraan kendaraan) {
        synchronized (this) {
            kendaraanRepository.saveKendaraan(kendaraan);
        }
    }

    public void updateKendaraan(Kendaraan kendaraan) {
        synchronized (this) {
            kendaraanRepository.updateKendaraan(kendaraan);
        }
    }

    public void deleteKendaraanById(String idKendaraan) {
        synchronized (this) {
            kendaraanRepository.deleteKendaraanById(idKendaraan);
        }
    }
    public boolean isKendaraanExist(Kendaraan kendaraan){
        return kendaraanRepository.findByName(kendaraan.getPlatKendaraan()).size() != 0;
    }
    @Override
    public List<Kendaraan> findWithPaging(int page, int limit) {
        return kendaraanRepository.findWithPaging(page, limit);
    }



}

