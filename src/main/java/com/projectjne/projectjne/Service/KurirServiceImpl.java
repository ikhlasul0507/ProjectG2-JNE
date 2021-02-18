package com.projectjne.projectjne.Service;
import com.projectjne.projectjne.Model.Kurir;
import com.projectjne.projectjne.Repository.KurirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("KurirService")
public class KurirServiceImpl implements KurirService {

    @Autowired
    KurirRepository kurirRepository;

    public List<Kurir> findAll() {
        List<Kurir> kurirs = kurirRepository.findAll();
        return kurirs;
    }

    public Kurir findById(String idKurir) {
        Kurir dh;
        try{
            dh = kurirRepository.findById(idKurir);
        }catch (EmptyResultDataAccessException e){
            System.out.println(e);
            dh = null;
        }
        return dh;
    }
    @Override
    public Kurir findByName(String name) {
        return (Kurir) kurirRepository.findByName(name).get(0);
    }

    public void saveKurir(Kurir kurir) {
        synchronized (this) {
            kurirRepository.saveKurir(kurir);
        }
    }

    public void updateKurir(Kurir kurir) {
        synchronized (this) {
            kurirRepository.updateKurir(kurir);
        }
    }

    public void deleteKurirById(String idKurir) {
        synchronized (this) {
            kurirRepository.deleteKurirById(idKurir);
        }
    }
    public boolean isKurirExist(Kurir kurir){
        return kurirRepository.findByName(kurir.getNamaKurir()).size() != 0;
    }
    @Override
    public List<Kurir> findWithPaging(int page, int limit) {
        return kurirRepository.findWithPaging(page, limit);
    }



}

