package com.projectjne.projectjne.Repository;
import com.projectjne.projectjne.Model.Kendaraan;
import com.projectjne.projectjne.Model.Kurir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.UUID;

@Repository("KurirRepository")
public class KurirRepositoryImpl implements KurirRepository{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Kurir> findAll() {
        List<Kurir> kurirs;
        kurirs = jdbcTemplate.query("Select * FROM tbl_kurir",
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
        for (Kurir ch : kurirs) {
            ch.setKendaraanList(jdbcTemplate.query("select * from tbl_kendaraan c, tbl_kurir p where " +
                            "c.idKendaraan =?",
                    preparedStatement -> preparedStatement.setString(1,ch.getIdKendaraan()),
                    (rs, rowNum) ->
                            new Kendaraan(
                                    rs.getString("idKendaraan"),
                                    rs.getString("platKendaraan")
                            )).get(0));
        }
        return kurirs;

    }

    // Add new customer
    public void saveKurir(Kurir kurir) {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy-hh:mm:ss");
        String tgl= f.format(date);
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        jdbcTemplate.update("INSERT INTO tbl_kurir (idKurir,idKendaraan,noKtp,namaKurir,handphone, jenisKelamin) VALUES (?,?,?,?,?,?)",
                tgl+"-"+randomUUIDString,kurir.getIdKendaraan(),kurir.getNoKtp(),kurir.getNamaKurir(),kurir.getHandphone(),kurir.getJenisKelamin());
    }
    // update new customer
    public void updateKurir(Kurir kurir) {
        jdbcTemplate.update("UPDATE tbl_kurir SET idKendaraan = ?,noKtp = ?,namaKurir = ?,handphone = ?, jenisKelamin= ? Where idKurir=?",
                kurir.getIdKendaraan(), kurir.getNoKtp(), kurir.getNamaKurir(), kurir.getHandphone(), kurir.getJenisKelamin(), kurir.getIdKurir());
    }

    public Kurir findById(String idKurir){
        Kurir kurir;
        kurir =  jdbcTemplate.queryForObject("SELECT * FROM tbl_kurir WHERE idKurir=?",new Object[]{idKurir},
                (rs,rowNum)->
                        new Kurir(
                                rs.getString("idKurir"),
                                rs.getString("idKendaraan"),
                                rs.getString("noKtp"),
                                rs.getString("namaKurir"),
                                rs.getString("handphone"),
                                rs.getString("jenisKelamin")
                        ));

        kurir.setKendaraanList(jdbcTemplate.query("select * from tbl_kendaraan c, tbl_kurir p where " +
                        "c.idKendaraan =?",
                preparedStatement -> preparedStatement.setString(1,kurir.getIdKendaraan()),
                (rs, rowNum) ->
                        new Kendaraan(
                                rs.getString("idKendaraan"),
                                rs.getString("platKendaraan")
                        )).get(0));
        return kurir;
    }
    public List<Kurir> findByName(String namaKurir){
        List<Kurir> kurir;
        kurir =  jdbcTemplate.query("Select * FROM tbl_kurir where namaKurir like ?",
                new Object[]{"%"+namaKurir+"%"},
                (rs,rowNum)->
                        new Kurir(
                                rs.getString("idKurir"),
                                rs.getString("idKendaraan"),
                                rs.getString("noKtp"),
                                rs.getString("namaKurir"),
                                rs.getString("handphone"),
                                rs.getString("jenisKelamin")
                        ));

        for (Kurir ch : kurir) {
            ch.setKendaraanList(jdbcTemplate.query("select * from tbl_kendaraan c, tbl_kurir p where " +
                            "c.idKendaraan =?",
                    preparedStatement -> preparedStatement.setString(1,ch.getIdKendaraan()),
                    (rs, rowNum) ->
                            new Kendaraan(
                                    rs.getString("idKendaraan"),
                                    rs.getString("platKendaraan")
                            )).get(0));
        }
        return kurir;
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

        List<Kurir> kurirs;
        kurirs = jdbcTemplate.query("Select * FROM tbl_kurir LIMIT " + start + "," + limit + ";",
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
        for (Kurir ch : kurirs) {
            ch.setKendaraanList(jdbcTemplate.query("select * from tbl_kendaraan c, tbl_kurir p where " +
                            "c.idKendaraan =?",
                    preparedStatement -> preparedStatement.setString(1,ch.getIdKendaraan()),
                    (rs, rowNum) ->
                            new Kendaraan(
                                    rs.getString("idKendaraan"),
                                    rs.getString("platKendaraan")
                            )).get(0));
        }
        return kurirs;
    }
}


