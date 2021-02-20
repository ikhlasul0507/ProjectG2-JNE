package com.projectjne.projectjne.Repository;

import com.projectjne.projectjne.Model.DaftarHarga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("DaftarHargaRepository")
public class DaftarHargaRepositoryImpl implements DaftarHargaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<DaftarHarga> findAll() {
        return jdbcTemplate.query("select * from tbl_daftar_harga",
                (rs, rowNum)->
                        new DaftarHarga(
                                rs.getString("idDaftarHarga"),
                                rs.getString("namaPaket"),
                                rs.getString("hargaPaket"),
                                rs.getInt("status")
                        )
        );
    }

    // Add new customer
    public void saveDaftarHarga(DaftarHarga daftarHarga) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        jdbcTemplate.update("INSERT INTO tbl_daftar_harga (idDaftarHarga,namaPaket, hargaPaket, status) VALUES (?,?,?,?)",
                randomUUIDString,daftarHarga.getNamaPaket(),daftarHarga.getHargaPaket(),daftarHarga.getStatus() );
    }
    // update new customer
    public void updateDaftarHarga(DaftarHarga daftarHarga) {
        jdbcTemplate.update("UPDATE tbl_daftar_harga SET namaPaket= ?, hargaPaket=?, status=? Where idDaftarHarga=?",
                daftarHarga.getNamaPaket(), daftarHarga.getHargaPaket(),daftarHarga.getStatus(), daftarHarga.getIdDaftarHarga());
    }

    public DaftarHarga findById(String idDaftarHarga){
        String sql = "select * from tbl_daftar_harga WHERE idDaftarHarga='"+idDaftarHarga+"'";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum)->
                        new DaftarHarga(
                                rs.getString("idDaftarHarga"),
                                rs.getString("namaPaket"),
                                rs.getString("hargaPaket"),
                                rs.getInt("status")

                        )
        );
    }
    public List<DaftarHarga> findByName(String namaPaket){
        return jdbcTemplate.query("Select * FROM tbl_daftar_harga where namaPaket like ?",
                new Object[]{"%"+namaPaket+"%"},
                (rs, rowNum)->
                        new DaftarHarga(
                                rs.getString("idDaftarHarga"),
                                rs.getString("namaPaket"),
                                rs.getString("hargaPaket"),
                                rs.getInt("status")
                        )
        );
    }

    public void deleteDaftarHargaById(String idDaftarHarga){
        jdbcTemplate.execute(" DELETE FROM tbl_daftar_harga WHERE idDaftarHarga='"+idDaftarHarga+"'");
    }
    @Override
    public List<DaftarHarga> findWithPaging(int page, int limit) {
        int numPages;
        numPages = jdbcTemplate.query("SELECT COUNT(*) as count FROM tbl_daftar_harga",
                (rs, rowNum) -> rs.getInt("count")).get(0);

        // validate page
        if(page < 1) page = 1;
        if(page > numPages) page = numPages;

        int start = (page - 1) * limit;

        System.out.println("start :"+start);
        System.out.println("limit :"+limit);
        List<DaftarHarga> daftarHargaList =
                jdbcTemplate.query("SELECT * FROM tbl_daftar_harga LIMIT " + start + "," + limit + ";",
                        (rs, rowNum) ->
                                new DaftarHarga(
                                        rs.getString("idDaftarHarga"),
                                        rs.getString("namaPaket"),
                                        rs.getString("hargaPaket"),
                                        rs.getInt("status")

                                ));
        return daftarHargaList;
    }
}

