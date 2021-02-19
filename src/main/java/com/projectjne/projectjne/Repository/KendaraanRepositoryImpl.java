package com.projectjne.projectjne.Repository;


import com.projectjne.projectjne.Model.DaftarHarga;
import com.projectjne.projectjne.Model.Kendaraan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("KendaraanRepository")
public class KendaraanRepositoryImpl implements KendaraanRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Kendaraan> findAll() {
        return jdbcTemplate.query("select * from tbl_kendaraan",
                (rs, rowNum)->
                        new Kendaraan(
                                rs.getString("idKendaraan"),
                                rs.getString("platKendaraan")
                        )
        );
    }

    // Add new customer
    public void saveKendaraan(Kendaraan kendaraan) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        jdbcTemplate.update("INSERT INTO tbl_kendaraan (idKendaraan,platKendaraan) VALUES (?,?)",
                randomUUIDString,kendaraan.getPlatKendaraan());
    }
    // update new customer
    public void updateKendaraan(Kendaraan kendaraan) {
        jdbcTemplate.update("UPDATE tbl_kendaraan SET platKendaraan= ? Where idKendaraan=?",
                kendaraan.getPlatKendaraan(),kendaraan.getIdKendaraan());
    }

    public Kendaraan findById(String idKendaraan){
        String sql = "select * from tbl_kendaraan WHERE idKendaraan='"+idKendaraan+"'";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum)->
                        new Kendaraan(
                                rs.getString("idKendaraan"),
                                rs.getString("platKendaraan")
                        )
        );
    }
    public List<Kendaraan> findByName(String platKendaraan){
        return jdbcTemplate.query("Select * FROM tbl_kendaraan where platKendaraan like ?",
                new Object[]{"%"+platKendaraan+"%"},
                (rs, rowNum)->
                        new Kendaraan(
                                rs.getString("idKendaraan"),
                                rs.getString("platKendaraan")
                        )
        );
    }

    public void deleteKendaraanById(String idKendaraan){
        jdbcTemplate.execute(" DELETE FROM tbl_kendaraan WHERE idKendaraan='"+idKendaraan+"'");
    }
    @Override
    public List<Kendaraan> findWithPaging(int page, int limit) {
        int numPages;
        numPages = jdbcTemplate.query("SELECT COUNT(*) as count FROM tbl_kendaraan",
                (rs, rowNum) -> rs.getInt("count")).get(0);

        // validate page
        if(page < 1) page = 1;
        if(page > numPages) page = numPages;

        int start = (page - 1) * limit;

        System.out.println("start :"+start);
        System.out.println("limit :"+limit);
        List<Kendaraan> kendaraanList =
                jdbcTemplate.query("SELECT * FROM tbl_kendaraan LIMIT " + start + "," + limit + ";",
                        (rs, rowNum) ->
                                new Kendaraan(
                                        rs.getString("idKendaraan"),
                                        rs.getString("platKendaraan")
                                )
                );
        return kendaraanList;
    }
}

