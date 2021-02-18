package com.projectjne.projectjne.Model;

public class DaftarHarga {
    private String idDaftarHarga,namaPaket,hargaPaket;

    public DaftarHarga(String idDaftarHarga, String namaPaket, String hargaPaket) {
        this.idDaftarHarga = idDaftarHarga;
        this.namaPaket = namaPaket;
        this.hargaPaket = hargaPaket;
    }

    public String getIdDaftarHarga() {
        return idDaftarHarga;
    }

    public void setIdDaftarHarga(String idDaftarHarga) {
        this.idDaftarHarga = idDaftarHarga;
    }

    public String getNamaPaket() {
        return namaPaket;
    }

    public void setNamaPaket(String namaPaket) {
        this.namaPaket = namaPaket;
    }

    public String getHargaPaket() {
        return hargaPaket;
    }

    public void setHargaPaket(String hargaPaket) {
        this.hargaPaket = hargaPaket;
    }

}
