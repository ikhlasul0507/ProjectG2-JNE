package com.projectjne.projectjne.Model;

public class Jadwal {
    private String idJadwal,idKurir,idKendaraan,idPaket;
    private int qty,kg,harga,total;

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
