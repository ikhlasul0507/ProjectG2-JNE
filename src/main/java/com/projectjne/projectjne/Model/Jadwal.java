package com.projectjne.projectjne.Model;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Jadwal {
    @NotNull
    private String idJadwal,idKurir,idKendaraan,idPaket;
    @NotNull
    private int qty,kg,harga,total;
    Kurir kurirList;
    Kendaraan kendaraanList;
    DaftarHarga daftarHargaList;

    public Jadwal(String idJadwal, String idKurir, String idKendaraan, String idPaket, int qty, int kg, int harga, int total) {
        this.idJadwal = idJadwal;
        this.idKurir = idKurir;
        this.idKendaraan = idKendaraan;
        this.idPaket = idPaket;
        this.qty = qty;
        this.kg = kg;
        this.harga = harga;
        this.total = total;
    }

    public Kurir getKurirList() {
        return kurirList;
    }

    public void setKurirList(Kurir kurirList) {
        this.kurirList = kurirList;
    }

    public Kendaraan getKendaraanList() {
        return kendaraanList;
    }

    public void setKendaraanList(Kendaraan kendaraanList) {
        this.kendaraanList = kendaraanList;
    }

    public DaftarHarga getDaftarHargaList() {
        return daftarHargaList;
    }

    public void setDaftarHargaList(DaftarHarga daftarHargaList) {
        this.daftarHargaList = daftarHargaList;
    }

    public String getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(String idJadwal) {
        this.idJadwal = idJadwal;
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

    public String getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(String idPaket) {
        this.idPaket = idPaket;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
