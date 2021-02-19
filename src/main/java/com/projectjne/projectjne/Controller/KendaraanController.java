package com.projectjne.projectjne.Controller;

import com.projectjne.projectjne.Model.Kendaraan;
import com.projectjne.projectjne.Service.KendaraanService;
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
public class KendaraanController {
    public static final Logger logger = LoggerFactory.getLogger(KendaraanController.class);
    @Autowired
    KendaraanService kendaraanService;
    //get all kendaraan
    @RequestMapping(value = "/kendaraan/", method = RequestMethod.GET)
    public ResponseEntity<List<Kendaraan>> listAllKendaraan() {
        List<Kendaraan> kendaraans = kendaraanService.findAll();
        if (kendaraans.isEmpty()) {
            return new ResponseEntity<>(kendaraans, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(kendaraans, HttpStatus.OK);
        }
    }

    //get kendaraan by id
    @RequestMapping(value = "/kendaraan/{idKendaraan}", method = RequestMethod.GET)
    public ResponseEntity<?> getKendaraan(@PathVariable("idKendaraan") String idKendaraan) {
        logger.info("Fetching kendaraan with id Kendaraan {}", idKendaraan);
        Kendaraan kendaraan = kendaraanService.findById(idKendaraan);
        if (kendaraan == null) {
            logger.error("kendaraan with idkendaraan Harga {} not found .", idKendaraan);
            return new ResponseEntity<>(new CustomErrorType("kendaraan with id " + idKendaraan + " not found"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(kendaraan, HttpStatus.OK);
        }
    }
    //get all kendaraan by name
    @RequestMapping(value = "/kendaraan/nama/{platKendaraan}", method = RequestMethod.GET)
    public ResponseEntity<?> getKendaraanByName(@PathVariable("platKendaraan") String platKendaraan) {
        logger.info("Fetching kendaraan with name {}", platKendaraan);
        Kendaraan kendaraan = kendaraanService.findByName(platKendaraan);

        if(kendaraan == null) {
            logger.error("kendaraan with name {} not found.", platKendaraan);
            return new ResponseEntity<>(new CustomErrorType("kendaraan with name " + platKendaraan + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(kendaraan, HttpStatus.OK);
    }

    //insert kendaraan
    @RequestMapping(value = "/kendaraan/", method = RequestMethod.POST)
    public ResponseEntity<?> createKendaraan(@RequestBody Kendaraan kendaraan) {
        logger.info("Creating Daftar Harga  : {} ", kendaraan);
        if (kendaraanService.isKendaraanExist(kendaraan)) {
            logger.error("Unable to create, kendaraan already exist", kendaraan.getPlatKendaraan());
            return new ResponseEntity<>(new CustomErrorType("Unable to create, kendaraan already" + kendaraan.getPlatKendaraan()), HttpStatus.CONFLICT);
        }else {
            kendaraanService.saveKendaraan(kendaraan);
            return new ResponseEntity<>("Data Berhasil Di Simpan", HttpStatus.OK);
        }
    }
    //delete kendaraan by id
    @RequestMapping(value = "/kendaraan/{idKendaraan}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteKendaraan(@PathVariable("idKendaraan") String idKendaraan) {
        logger.info("Fetching & Deleting kendaraan with id Kendaraan {}", idKendaraan);

        Kendaraan kendaraan = kendaraanService.findById(idKendaraan);
        if (kendaraan == null) {
            logger.error("Unable to delete. kendaraan with id {} not found.", idKendaraan);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. kendaraan with id kendaraan " + idKendaraan + " not found."),
                    HttpStatus.NOT_FOUND);
        }else {
            kendaraanService.deleteKendaraanById(idKendaraan);
            return new ResponseEntity<>("Data Berhasil Di Hapus", HttpStatus.OK);
        }
    }
    //update kendaraan by id
    @RequestMapping(value = "/kendaraan/{idKendaraan}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateKendaraan(@PathVariable("idKendaraan") String idKendaraan, @RequestBody Kendaraan kendaraan) {
        logger.info("Updating kendaraan with id {}", idKendaraan);

        Kendaraan currentKendaraan = kendaraanService.findById(idKendaraan);

        if (currentKendaraan == null) {
            logger.error("Unable to update. kendaraan with id {} not found.", idKendaraan);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. kendaraan with id " + idKendaraan + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentKendaraan.setPlatKendaraan(kendaraan.getPlatKendaraan());

        kendaraanService.updateKendaraan(currentKendaraan);
        return new ResponseEntity<>(currentKendaraan, HttpStatus.OK);
    }
    //get kendaraan paging
    @GetMapping("/kendaraan/paging/")
    public ResponseEntity<?> getKendaraanPaging(@RequestParam int page, @RequestParam int limit) {
        List<Kendaraan> kendaraans = kendaraanService.findWithPaging(page, limit);
        if(kendaraans.isEmpty()) {
            return new ResponseEntity<>(kendaraans, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(kendaraans, HttpStatus.OK);
    }

}

