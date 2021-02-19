package com.projectjne.projectjne.Model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Kendaraan {
    @NotNull(message = "Id Kendaraan may not be null")
    private String idKendaraan;
    @NotBlank(message = "Plat Kendaraan may not be null")
    private String platKendaraan;

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
