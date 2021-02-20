package com.projectjne.projectjne.Model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EntityScan
public class DaftarHarga {
    @NotNull
    private String idDaftarHarga;
    @NotBlank(message = "Name may not be null")
    @Size(min = 0, max = 50)
    private String namaPaket;
    @NotBlank(message = "harga may not be null")
    private String hargaPaket;
    @NotBlank(message = "status may not be null")
    private int status;

    public DaftarHarga(@NotNull String idDaftarHarga, @NotBlank(message = "Name may not be null") @Size(min = 0, max = 50) String namaPaket, @NotBlank(message = "harga may not be null") String hargaPaket, @NotBlank(message = "status may not be null") int status) {
        this.idDaftarHarga = idDaftarHarga;
        this.namaPaket = namaPaket;
        this.hargaPaket = hargaPaket;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
