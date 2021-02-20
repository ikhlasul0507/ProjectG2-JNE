package com.projectjne.projectjne.Controller;

import com.projectjne.projectjne.Model.DaftarHarga;
import com.projectjne.projectjne.Service.DaftarHargaService;
import com.projectjne.projectjne.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("jne")
public class DaftarHargaController {
    public static final Logger logger = LoggerFactory.getLogger(DaftarHargaController.class);
    @Autowired
    DaftarHargaService daftarHargaService;
    //get all daftar harga
    @RequestMapping(value = "/harga/", method = RequestMethod.GET)
    public ResponseEntity<List<DaftarHarga>> listAllDaftarHarga() {
        List<DaftarHarga> daftarHargas = daftarHargaService.findAll();
        if (daftarHargas.isEmpty()) {
            return new ResponseEntity<>(daftarHargas, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(daftarHargas, HttpStatus.OK);
        }
    }

    //get daftar harga by id
    @RequestMapping(value = "/harga/{idDaftarHarga}", method = RequestMethod.GET)
    public ResponseEntity<?> getDaftarHarga(@PathVariable("idDaftarHarga") String idDaftarHarga) {
        logger.info("Fetching daftar harga with id DaftarHarga {}", idDaftarHarga);
        DaftarHarga daftarHarga = daftarHargaService.findById(idDaftarHarga);
        if (daftarHarga == null) {
            logger.error("Daftar Harga with idDaftar Harga {} not found .", idDaftarHarga);
            return new ResponseEntity<>(new CustomErrorType("Product with id " + idDaftarHarga + " not found"), HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(daftarHarga, HttpStatus.OK);
        }
    }
    //get all daftar harga by name
    @RequestMapping(value = "/harga/nama/{namaPaket}", method = RequestMethod.GET)
    public ResponseEntity<?> getDaftaHargaByName(@PathVariable("namaPaket") String namaPaket) {
        logger.info("Fetching daftar harga with name {}", namaPaket);
        DaftarHarga daftarHarga = daftarHargaService.findByName(namaPaket);

        if(daftarHarga == null) {
            logger.error("daftar harga with name {} not found.", namaPaket);
            return new ResponseEntity<>(new CustomErrorType("daftar harga with name " + namaPaket + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(daftarHarga, HttpStatus.OK);
    }

    //insert daftar harga
    @RequestMapping(value = "/harga/", method = RequestMethod.POST)
    public ResponseEntity<?> createDaftarHarga(@RequestBody DaftarHarga daftarHarga) {
        logger.info("Creating Daftar Harga  : {} ", daftarHarga);
        if(daftarHarga.getNamaPaket() ==""){
            return new ResponseEntity<>(new CustomErrorType("nama paket not found."),
                    HttpStatus.NOT_FOUND);
        }else if(daftarHarga.getHargaPaket() ==""){
            return new ResponseEntity<>(new CustomErrorType("harga paket not found."),
                    HttpStatus.NOT_FOUND);
        }else if (daftarHargaService.isDaftarHargaExist(daftarHarga)) {
            logger.error("Unable to create, daftar harga already exist", daftarHarga.getNamaPaket());
            return new ResponseEntity<>(new CustomErrorType("Unable to create, daftar harga already" + daftarHarga.getNamaPaket()), HttpStatus.CONFLICT);
        }else {
            daftarHargaService.saveDaftarHarga(daftarHarga);
            return new ResponseEntity<>("Data Berhasil Di Simpan", HttpStatus.OK);
        }
    }
    //delete daftar harga by id
    @RequestMapping(value = "/harga/{idDaftarHarga}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDaftarHarga(@PathVariable("idDaftarHarga") String idDaftarHarga) {
        logger.info("Fetching & Deleting daftar harga with id Merk {}", idDaftarHarga);

        DaftarHarga daftarHarga = daftarHargaService.findById(idDaftarHarga);
        if (daftarHarga == null) {
            logger.error("Unable to delete. daftar harga with id {} not found.", idDaftarHarga);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Product with id merk " + idDaftarHarga + " not found."),
                    HttpStatus.NOT_FOUND);
        }else {
            daftarHargaService.deleteDaftarHargaById(idDaftarHarga);
            return new ResponseEntity<>("Data Berhasil Di Hapus", HttpStatus.OK);
        }
    }
    //update daftar harga by id
    @RequestMapping(value = "/harga/{idDaftarHarga}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDaftarHarga(@PathVariable("idDaftarHarga") String idDaftarHarga, @RequestBody DaftarHarga daftarHarga) {
        logger.info("Updating daftar harga with id {}", idDaftarHarga);

        DaftarHarga currentDaftarHarga = daftarHargaService.findById(idDaftarHarga);

        if (currentDaftarHarga == null) {
            logger.error("Unable to update. daftar harga with id {} not found.", idDaftarHarga);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. daftar harga with id " + idDaftarHarga + " not found."),
                    HttpStatus.NOT_FOUND);
        }else if(daftarHarga.getNamaPaket() ==""){
            return new ResponseEntity<>(new CustomErrorType("nama paket not found."),
                    HttpStatus.NOT_FOUND);
        }else if(daftarHarga.getHargaPaket() ==""){
            return new ResponseEntity<>(new CustomErrorType("harga paket not found."),
                    HttpStatus.NOT_FOUND);
        }else {
            currentDaftarHarga.setNamaPaket(daftarHarga.getNamaPaket());
            currentDaftarHarga.setHargaPaket(daftarHarga.getHargaPaket());
            currentDaftarHarga.setStatus(daftarHarga.getStatus());

            daftarHargaService.updateDaftarHarga(currentDaftarHarga);
            return new ResponseEntity<>(currentDaftarHarga, HttpStatus.OK);
        }
    }
    //get daftar harga paging
    @GetMapping("/harga/paging/")
    public ResponseEntity<?> getDaftarHargaPaging(@RequestParam int page, @RequestParam int limit) {
        List<DaftarHarga> daftarHargas = daftarHargaService.findWithPaging(page, limit);
        if(daftarHargas.isEmpty()) {
            return new ResponseEntity<>(daftarHargas, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(daftarHargas, HttpStatus.OK);
    }

}
