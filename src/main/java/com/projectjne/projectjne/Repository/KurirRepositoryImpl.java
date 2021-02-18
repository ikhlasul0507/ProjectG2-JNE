package com.projectjne.projectjne.Repository;

import com.projectjne.projectjne.Model.DaftarHarga;
import com.projectjne.projectjne.Model.Kurir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("KurirRepository")
public class KurirRepositoryImpl implements KurirRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Kurir> findAll() {
        return jdbcTemplate.query("select * from tbl_kurir",
                (rs, rowNum)->
                        new Kurir(
                                rs.getString("idKurir"),
                                rs.getString("idKendaraan"),
                                rs.getString("noKtp"),
                                rs.getString("namaKurir"),
                                rs.getString("handphone"),
                                rs.getString("jenisKelamin")
                        )
        );
    }

    // Add new customer
    public void saveKurir(Kurir kurir) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        jdbcTemplate.update("INSERT INTO tbl_kurir (idKurir,idKendaraan,noKtp,namaKurir,handphone, jenisKelamin) VALUES (?,?,?,?,?,?)",
                randomUUIDString,kurir.getIdKendaraan(),kurir.getNoKtp(),kurir.getNamaKurir(),kurir.getHandphone(),kurir.getJenisKelamin());
    }
    // update new customer
    public void updateKurir(Kurir kurir) {
        jdbcTemplate.update("UPDATE tbl_kurir SET idKendaraan = ?,noKtp = ?,namaKurir = ?,handphone = ?, jenisKelamin= ? Where idKurir=?",
                kurir.getIdKendaraan(), kurir.getNoKtp(), kurir.getNamaKurir(), kurir.getHandphone(), kurir.getJenisKelamin(), kurir.getIdKurir());
    }

    public Kurir findById(String idKurir){
        String sql = "select * from tbl_kurir WHERE idKurir='"+idKurir+"'";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum)->
                        new Kurir(
                                rs.getString("idKurir"),
                                rs.getString("idKendaraan"),
                                rs.getString("noKtp"),
                                rs.getString("namaKurir"),
                                rs.getString("handphone"),
                                rs.getString("jenisKelamin")
                        )
        );
    }
    public List<Kurir> findByName(String namaKurir){
        return jdbcTemplate.query("Select * FROM tbl_kurir where namaKurir like ?",
                new Object[]{"%"+namaKurir+"%"},
                (rs, rowNum)->
                        new Kurir(
                                rs.getString("idKurir"),
                                rs.getString("idKendaraan"),
                                rs.getString("noKtp"),
                                rs.getString("namaKurir"),
                                rs.getString("handphone"),
                                rs.getString("jenisKelamin")
                        )
        );
    }

    public void deleteKurirById(String idKurir){
        jdbcTemplate.execute(" DELETE FROM tbl_kurir WHERE idKurir='"+idKurir+"'");
    }
    @Override
    public List<Kurir> findWithPaging(int page, int limit) {
        int numPages;
        numPages = jdbcTemplate.query("SELECT COUNT(*) as count FROM tbl_kurir",
                (rs, rowNum) -> rs.getInt("count")).get(0);

        // validate page
        if(page < 1) page = 1;
        if(page > numPages) page = numPages;

        int start = (page - 1) * limit;

        System.out.println("start :"+start);
        System.out.println("limit :"+limit);
        List<Kurir> kurirList =
                jdbcTemplate.query("SELECT * FROM tbl_kurir LIMIT " + start + "," + limit + ";",
                        (rs, rowNum) ->
                                new Kurir(
                                        rs.getString("idKurir"),
                                        rs.getString("idKendaraan"),
                                        rs.getString("noKtp"),
                                        rs.getString("namaKurir"),
                                        rs.getString("handphone"),
                                        rs.getString("jenisKelamin")
                                )
                );
        return kurirList;
    }
}


