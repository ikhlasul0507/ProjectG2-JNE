package com.projectjne.projectjne.Controller;

import com.projectjne.projectjne.Model.Jadwal;
import com.projectjne.projectjne.Model.Jadwal;
import com.projectjne.projectjne.Service.JadwalService;
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
public class JadwalController {
    public static final Logger logger = LoggerFactory.getLogger(JadwalController.class);
    @Autowired
    JadwalService jadwalService;
    //get all jadwal
    @RequestMapping(value = "/jadwal/", method = RequestMethod.GET)
    public ResponseEntity<List<Jadwal>> listAllJadwal() {
        List<Jadwal> jadwals = jadwalService.findAll();
        if (jadwals.isEmpty()) {
            return new ResponseEntity<>(jadwals, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(jadwals, HttpStatus.OK);
        }
    }
    //get jadwal by id
    @RequestMapping(value = "/jadwal/{idJadwal}", method = RequestMethod.GET)
    public ResponseEntity<?> getJadwal(@PathVariable("idJadwal") String idJadwal) {
        logger.info("Fetching jadwal with id Jadwal {}", idJadwal);
        Jadwal jadwal = jadwalService.findById(idJadwal);
        if (jadwal == null) {
            logger.error("jadwal with idjadwal Harga {} not found .", idJadwal);
            return new ResponseEntity<>(new CustomErrorType("jadwal with id " + idJadwal + " not found"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(jadwal, HttpStatus.OK);
        }
    }
    //get jadwal by id paket
    @RequestMapping(value = "/jadwal/paket/{idPaket}", method = RequestMethod.GET)
    public ResponseEntity<?> getJadwalidPaket(@PathVariable("idPaket") String idPaket) {
        logger.info("Fetching jadwal with id Jadwal {}", idPaket);
        Jadwal jadwal = jadwalService.findByIdPaket(idPaket);
        if (jadwal == null) {
            logger.error("jadwal with idpaket {} not found .", idPaket);
            return new ResponseEntity<>(new CustomErrorType("jadwal with id " + idPaket + " not found"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(jadwal, HttpStatus.OK);
        }
    }
    //get jadwal by id kurir
    @RequestMapping(value = "/jadwal/kurir/{idKurir}", method = RequestMethod.GET)
    public ResponseEntity<?> getJadwalidKurir(@PathVariable("idKurir") String idKurir) {
        logger.info("Fetching jadwal with id Jadwal {}", idKurir);
        Jadwal jadwal = jadwalService.findByIdKurir(idKurir);
        if (jadwal == null) {
            logger.error("jadwal with idpaket {} not found .", idKurir);
            return new ResponseEntity<>(new CustomErrorType("jadwal with id " + idKurir + " not found"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(jadwal, HttpStatus.OK);
        }
    }

    //insert jadwal
    @RequestMapping(value = "/jadwal/", method = RequestMethod.POST)
    public ResponseEntity<?> createJadwal(@RequestBody Jadwal jadwal) {
        logger.info("Creating Jadwal  : {} ", jadwal);
        System.out.println("name :"+jadwal.getIdKurir());
        if(jadwal.getIdKurir() == ""){
            return new ResponseEntity<>(new CustomErrorType("id kurir not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getIdJadwal() == ""){
            return new ResponseEntity<>(new CustomErrorType("id jadwal not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getIdPaket() == ""){
            return new ResponseEntity<>(new CustomErrorType("id Paket not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getQty() == 0 ){
            return new ResponseEntity<>(new CustomErrorType("qty not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getKg() == 0){
            return new ResponseEntity<>(new CustomErrorType("kg not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getHarga() == 0){
            return new ResponseEntity<>(new CustomErrorType("harga not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getTotal() == 0){
            return new ResponseEntity<>(new CustomErrorType("total not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwalService.isJadwalExist(jadwal)) {
            logger.error("Unable to create, jadwal already exist", jadwal.getIdPaket());
            return new ResponseEntity<>(new CustomErrorType("Unable to create, id paket already" + jadwal.getIdPaket()), HttpStatus.CONFLICT);
        }else{
            jadwalService.saveJadwal(jadwal);
            return new ResponseEntity<>("Data Berhasil Di Simpan", HttpStatus.OK);
        }
    }
    //delete jadwal by id
    @RequestMapping(value = "/jadwal/{idJadwal}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteJadwal(@PathVariable("idJadwal") String idJadwal) {
        logger.info("Fetching & Deleting jadwal with id Jadwal {}", idJadwal);
        if(idJadwal == null){
            return new ResponseEntity<>(new CustomErrorType("id Jadwal not found."),
                    HttpStatus.NOT_FOUND);
        }else {
            Jadwal jadwal = jadwalService.findById(idJadwal);
            if (jadwal == null) {
                logger.error("Unable to delete. jadwal with id {} not found.", idJadwal);
                return new ResponseEntity<>(new CustomErrorType("Unable to delete. jadwal with id jadwal " + idJadwal + " not found."),
                        HttpStatus.NOT_FOUND);
            } else {
                jadwalService.deleteJadwalById(idJadwal);
                return new ResponseEntity<>("Data Berhasil Di Hapus", HttpStatus.OK);
            }
        }
    }
    //update jadwal by id
    @RequestMapping(value = "/jadwal/{idJadwal}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateJadwal(@PathVariable("idJadwal") String idJadwal, @RequestBody Jadwal jadwal) {
        logger.info("Updating jadwal with id {}", idJadwal);
        Jadwal currentJadwal = jadwalService.findById(idJadwal);
        if (currentJadwal == null) {
            logger.error("Unable to update. jadwal with id {} not found.", idJadwal);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. jadwal with id " + idJadwal + " not found."),
                    HttpStatus.NOT_FOUND);
        }if(jadwal.getIdKurir() == ""){
            return new ResponseEntity<>(new CustomErrorType("id kurir not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getIdJadwal() == ""){
            return new ResponseEntity<>(new CustomErrorType("id jadwal not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getIdPaket() == ""){
            return new ResponseEntity<>(new CustomErrorType("id Paket not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getQty() == 0){
            return new ResponseEntity<>(new CustomErrorType("qty not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getKg() == 0){
            return new ResponseEntity<>(new CustomErrorType("kg not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getHarga() == 0){
            return new ResponseEntity<>(new CustomErrorType("harga not null"),
                    HttpStatus.NOT_FOUND);
        }else if(jadwal.getTotal() == 0){
            return new ResponseEntity<>(new CustomErrorType("total not null"),
                    HttpStatus.NOT_FOUND);
        }else {
            currentJadwal.setIdKurir(jadwal.getIdKurir());
            currentJadwal.setIdJadwal(jadwal.getIdJadwal());
            currentJadwal.setIdPaket(jadwal.getIdPaket());
            currentJadwal.setQty(jadwal.getQty());
            currentJadwal.setKg(jadwal.getKg());
            currentJadwal.setHarga(jadwal.getHarga());
            currentJadwal.setTotal(jadwal.getTotal());
            jadwalService.updateJadwal(currentJadwal);
            return new ResponseEntity<>(currentJadwal, HttpStatus.OK);
        }
    }
    //get jadwal paging
    @GetMapping("/jadwal/paging/")
    public ResponseEntity<?> getJadwalPaging(@RequestParam int page, @RequestParam int limit) {
        List<Jadwal> jadwals = jadwalService.findWithPaging(page, limit);
        if (jadwals.isEmpty()) {
            return new ResponseEntity<>(jadwals, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(jadwals, HttpStatus.OK);
    }

}


