package com.projectjne.projectjne.Controller;

import com.projectjne.projectjne.Model.DaftarHarga;
import com.projectjne.projectjne.Model.Kurir;
import com.projectjne.projectjne.Service.DaftarHargaService;
import com.projectjne.projectjne.Service.KurirService;
import com.projectjne.projectjne.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("jne")
public class KurirController {
    public static final Logger logger = LoggerFactory.getLogger(KurirController.class);
    @Autowired
    KurirService kurirService;
    //get all daftar harga
    @RequestMapping(value = "/kurir/", method = RequestMethod.GET)
    public ResponseEntity<List<Kurir>> listAllKurir() {
        List<Kurir> kurirs = kurirService.findAll();
        if (kurirs.isEmpty()) {
            return new ResponseEntity<>(kurirs, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(kurirs, HttpStatus.OK);
        }
    }

    //get daftar harga by id
    @RequestMapping(value = "/kurir/{idKurir}", method = RequestMethod.GET)
    public ResponseEntity<?> getKurir(@PathVariable("idKurir") String idKurir) {
        logger.info("Fetching kurir with id kurir {}", idKurir);
        Kurir kurir = kurirService.findById(idKurir);
        if (kurir == null) {
            logger.error("Daftar kurir with idkurir {} not found .", idKurir);
            return new ResponseEntity<>(new CustomErrorType("Product with id " + idKurir + " not found"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(kurir, HttpStatus.OK);
        }
    }
    //get all daftar harga by name
    @RequestMapping(value = "/kurir/nama/{namaKurir}", method = RequestMethod.GET)
    public ResponseEntity<?> getKurirByName(@PathVariable("namaKurir") String namaKurir) {
        logger.info("Fetching kurir with name {}", namaKurir);
        Kurir kurir = kurirService.findByName(namaKurir);

        if(kurir == null) {
            logger.error("daftar kurir with name {} not found.", namaKurir);
            return new ResponseEntity<>(new CustomErrorType("daftar kurir with name " + namaKurir + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(kurir, HttpStatus.OK);
    }

    //insert kuriur
    @RequestMapping(value = "/kurir/", method = RequestMethod.POST)
    public ResponseEntity<?> createKurir(@RequestBody Kurir kurir) {
        logger.info("Creating Kurir  : {} ", kurir);
        if (kurirService.isKurirExist(kurir)) {
            logger.error("Unable to create, kurir already exist", kurir.getNamaKurir());
            return new ResponseEntity<>(new CustomErrorType("Unable to create, kurir already" + kurir.getNamaKurir()), HttpStatus.CONFLICT);
        }else {
            kurirService.saveKurir(kurir);
            return new ResponseEntity<>("Data Berhasil Di Simpan", HttpStatus.OK);
        }
    }
    //delete daftar harga by id
    @RequestMapping(value = "/kurir/{idKurir}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteKurir(@PathVariable("idKurir") String idKurir) {
        logger.info("Fetching & Deleting Kurir with id kurir {}", idKurir);

        Kurir kurir = kurirService.findById(idKurir);
        if (kurir == null) {
            logger.error("Unable to delete. kasir with id {} not found.", idKurir);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. kurir with id kurir " + idKurir + " not found."),
                    HttpStatus.NOT_FOUND);
        }else {
            kurirService.deleteKurirById(idKurir);
            return new ResponseEntity<>("Data Berhasil Di Hapus", HttpStatus.OK);
        }
    }
    //update daftar harga by id
    @RequestMapping(value = "/kurir/{idKurir}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateKurir(@PathVariable("idKurir") String idKurir, @RequestBody Kurir kurir) {
        logger.info("Updating kurir with id {}", idKurir);

        Kurir currentKurir = kurirService.findById(idKurir);

        if (currentKurir == null) {
            logger.error("Unable to update. kurir with id {} not found.", idKurir);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. kurir with id " + idKurir + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentKurir.setNamaKurir(kurir.getNamaKurir());
        currentKurir.setIdKendaraan(kurir.getIdKendaraan());
        currentKurir.setNoKtp(kurir.getNoKtp());
        currentKurir.setHandphone(kurir.getHandphone());
        currentKurir.setJenisKelamin(kurir.getJenisKelamin());

        kurirService.updateKurir(currentKurir);
        return new ResponseEntity<>(currentKurir, HttpStatus.OK);
    }
    //get daftar harga paging
    @GetMapping("/kurir/paging/")
    public ResponseEntity<?> getKurirPaging(@RequestParam int page, @RequestParam int limit) {
        List<Kurir> kurirs = kurirService.findWithPaging(page, limit);
        if(kurirs.isEmpty()) {
            return new ResponseEntity<>(kurirs, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(kurirs, HttpStatus.OK);
    }

}
