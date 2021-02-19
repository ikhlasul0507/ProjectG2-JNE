package com.projectjne.projectjne.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Kurir {
    private String idKurir;
    @NotNull(message ="id Kendaraan not Null")
    private String idKendaraan;
    @Size(min = 0, max = 50)
    private String namaKurir;
    @NotNull(message = "Jenis Kelamin may not be null")
    @Size(min = 0, max = 1)
    private String jenisKelamin;
    @NotNull(message = "No Ktp may not be null")
    @Size(min = 0, max = 16)
    private String noKtp;
    @NotNull(message = "Handphone may not be null")
    @Size(min = 0, max = 14)
    private String handphone;
    Kendaraan kendaraanList;

    public Kurir(String idKurir, String idKendaraan, String noKtp, String namaKurir, String handphone, String jeniKelamin) {
        this.idKurir = idKurir;
        this.idKendaraan = idKendaraan;
        this.namaKurir = namaKurir;
        this.noKtp = noKtp;
        this.handphone = handphone;
        this.jenisKelamin = jeniKelamin;
    }

    public Kendaraan getKendaraanList() {
        return kendaraanList;
    }

    public void setKendaraanList(Kendaraan kendaraanList) {
        this.kendaraanList = kendaraanList;
    }

    public String getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(String idKurir) {
        this.idKurir = idKurir;
    }

    public String getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(String idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public String getNamaKurir() {
        return namaKurir;
    }

    public void setNamaKurir(String namaKurir) {
        this.namaKurir = namaKurir;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(String noKtp) {
        this.noKtp = noKtp;
    }

    public String getHandphone() {
        return handphone;
    }

    public void setHandphone(String handphone) {
        this.handphone = handphone;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}
