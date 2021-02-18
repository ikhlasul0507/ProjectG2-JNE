package com.projectjne.projectjne.Model;

public class Kurir {
    private String idKurir,idKendaraan,namaKurir;
    private int noKtp,handphone;
    private char jenisKelamin;

    public Kurir(String idKurir, String idKendaraan, String namaKurir, int noKtp, int handphone, char jenisKelamin) {
        this.idKurir = idKurir;
        this.idKendaraan = idKendaraan;
        this.namaKurir = namaKurir;
        this.noKtp = noKtp;
        this.handphone = handphone;
        this.jenisKelamin = jenisKelamin;
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

    public int getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(int noKtp) {
        this.noKtp = noKtp;
    }

    public int getHandphone() {
        return handphone;
    }

    public void setHandphone(int handphone) {
        this.handphone = handphone;
    }

    public char getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(char jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }
}
