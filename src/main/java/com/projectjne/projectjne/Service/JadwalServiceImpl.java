package com.projectjne.projectjne.Service;
import com.projectjne.projectjne.Model.Jadwal;
import com.projectjne.projectjne.Model.Jadwal;
import com.projectjne.projectjne.Repository.JadwalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("JadwalService")
public class JadwalServiceImpl implements JadwalService {

    @Autowired
    JadwalRepository jadwalRepository;

    public List<Jadwal> findAll() {
        List<Jadwal> jadwals = jadwalRepository.findAll();
        return jadwals;
    }

    public Jadwal findById(String idJadwal) {
        Jadwal dh;
        try{
            dh = jadwalRepository.findById(idJadwal);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            dh = null;
        }
        return dh;
    }
    public Jadwal findByIdKurir(String idKurir) {
        return (Jadwal) jadwalRepository.findByIdKurir(idKurir).get(0);
    }
    public Jadwal findByIdPaket(String idPaket) {
        return (Jadwal) jadwalRepository.findByIdPaket(idPaket).get(0);
    }
    public void saveJadwal(Jadwal jadwal) {
        synchronized (this) {
            jadwalRepository.saveJadwal(jadwal);
        }
    }

    public void updateJadwal(Jadwal jadwal) {
        synchronized (this) {
            jadwalRepository.updateJadwal(jadwal);
        }
    }

    public void deleteJadwalById(String idJadwal) {
        synchronized (this) {
            jadwalRepository.deleteJadwalById(idJadwal);
        }
    }
    public boolean isJadwalExist(Jadwal jadwal){
        return jadwalRepository.findByIdPaket(jadwal.getIdPaket()).size() != 0;
    }
    @Override
    public List<Jadwal> findWithPaging(int page, int limit) {
        return jadwalRepository.findWithPaging(page, limit);
    }

}
