package com.projectjne.projectjne.Model;

public class Kendaraan {
    private String idKendaraan,platKendaraan;

    public Kendaraan(String idKendaraan, String platKendaraan) {
        this.idKendaraan = idKendaraan;
        this.platKendaraan = platKendaraan;
    }

    public String getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(String idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public String getPlatKendaraan() {
        return platKendaraan;
    }

    public void setPlatKendaraan(String platKendaraan) {
        this.platKendaraan = platKendaraan;
    }
}
