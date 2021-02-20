package com.projectjne.projectjne.Repository;

import com.projectjne.projectjne.Model.DaftarHarga;
import com.projectjne.projectjne.Model.Jadwal;
import com.projectjne.projectjne.Model.Kendaraan;
import com.projectjne.projectjne.Model.Kurir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository("JadwalRepository")
public class JadwalRepositoryImpl implements JadwalRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Jadwal> findAll() {
        List<Jadwal> jadwalList;
        jadwalList = jdbcTemplate.query("Select * FROM tbl_jadwal_header",
                (rs, rowNum) ->
                        new Jadwal(
                                rs.getString("idJadwal"),
                                rs.getString("idKurir"),
                                rs.getString("idKendaraan"),
                                rs.getString("idPaket"),
                                rs.getInt("qty"),
                                rs.getInt("kg"),
                                rs.getInt("harga"),
                                rs.getInt("total")
                        )
        );
        for (Jadwal ch : jadwalList) {
            ch.setKurirList(jdbcTemplate.query("select * from tbl_kurir c, tbl_jadwal_header p where " +
                            "c.idKurir =?",
                    preparedStatement -> preparedStatement.setString(1, ch.getIdKurir()),
                    (rs, rowNum) ->
                            new Kurir(
                                    rs.getString("idKurir"),
                                    rs.getString("idKendaraan"),
                                    rs.getString("noKtp"),
                                    rs.getString("namaKurir"),
                                    rs.getString("handphone"),
                                    rs.getString("jenisKelamin")
                            )
            ).get(0));
            ch.getKurirList().setKendaraanList(
                    this.jdbcTemplate.query("SELECT k.idKendaraan, k.platKendaraan FROM tbl_kendaraan k, tbl_jadwal_header t WHERE k.idKendaraan = t.idKendaraan AND t.idKendaraan = ?",
                            preparedStatement -> preparedStatement.setString(1, ch.getIdKendaraan()),
                            (rs, rowNum) ->
                                    new Kendaraan(
                                            rs.getString("idKendaraan"),
                                            rs.getString("platKendaraan")
                                    )).get(0));
        }
        for (Jadwal jd : jadwalList) {
            jd.setKendaraanList(jdbcTemplate.query("select * from tbl_kendaraan c, tbl_jadwal_header p where " +
                            "c.idKendaraan =?",
                    preparedStatement -> preparedStatement.setString(1, jd.getIdKendaraan()),
                    (rs, rowNum) ->
                            new Kendaraan(
                                    rs.getString("idKendaraan"),
                                    rs.getString("platKendaraan")
                            )).get(0));
        }
        for (Jadwal ch : jadwalList) {
            ch.setDaftarHargaList((jdbcTemplate.query("select * from tbl_daftar_harga c, tbl_jadwal_header p where " +
                            "c.idDaftarHarga =?",
                    preparedStatement -> preparedStatement.setString(1, ch.getIdPaket()),
                    (rs, rowNum) ->
                            new DaftarHarga(
                                    rs.getString("idDaftarHarga"),
                                    rs.getString("namaPaket"),
                                    rs.getString("hargaPaket"),
                                    rs.getInt("status")
                            )
            )).get(0));
        }

        return jadwalList;
    }

    // Add new customer
    public void saveJadwal(Jadwal jadwal) {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy-hh:mm:ss");
        String tgl = f.format(date);
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        jdbcTemplate.update("INSERT INTO tbl_jadwal_header (idJadwal,idKurir,idKendaraan,idPaket,qty,kg,harga,total) VALUES (?,?,?,?,?,?,?,?)",
                tgl + "-" + randomUUIDString, jadwal.getIdKurir(), jadwal.getIdKendaraan(), jadwal.getIdPaket(), jadwal.getQty(), jadwal.getKg(), jadwal.getHarga(), jadwal.getTotal());
    }

    // update new customer
    public void updateJadwal(Jadwal jadwal) {
        jdbcTemplate.update("UPDATE tbl_jadwal_header SET idKurir=?,idKendaraan=?,idPaket=?,qty=?,kg=?,harga=?,total=? Where idJadwal=?",
                jadwal.getIdKurir(), jadwal.getIdKendaraan(), jadwal.getIdPaket(), jadwal.getQty(), jadwal.getKg(), jadwal.getHarga(), jadwal.getTotal(), jadwal.getIdJadwal());
    }

    public Jadwal findById(String idJadwal) {
        Jadwal jadwal;
        jadwal = jdbcTemplate.queryForObject("SELECT * FROM tbl_jadwal_header WHERE idJadwal=?", new Object[]{idJadwal},
                (rs, rowNum) ->
                        new Jadwal(
                                rs.getString("idJadwal"),
                                rs.getString("idKurir"),
                                rs.getString("idKendaraan"),
                                rs.getString("idPaket"),
                                rs.getInt("qty"),
                                rs.getInt("kg"),
                                rs.getInt("harga"),
                                rs.getInt("total")
                        )
        );
        jadwal.setKurirList(jdbcTemplate.query("select * from tbl_kurir c, tbl_jadwal_header p where " +
                        "c.idKurir =?",
                preparedStatement -> preparedStatement.setString(1, jadwal.getIdKurir()),
                (rs, rowNum) ->
                        new Kurir(
                                rs.getString("idKurir"),
                                rs.getString("idKendaraan"),
                                rs.getString("noKtp"),
                                rs.getString("namaKurir"),
                                rs.getString("handphone"),
                                rs.getString("jenisKelamin")
                        )
        ).get(0));
        jadwal.getKurirList().setKendaraanList(
                this.jdbcTemplate.query("SELECT k.idKendaraan, k.platKendaraan FROM tbl_kendaraan k, tbl_jadwal_header t WHERE k.idKendaraan = t.idKendaraan AND t.idKendaraan = ?",
                        preparedStatement -> preparedStatement.setString(1, jadwal.getIdKendaraan()),
                        (rs, rowNum) ->
                                new Kendaraan(
                                        rs.getString("idKendaraan"),
                                        rs.getString("platKendaraan")
                                )).get(0));

        jadwal.setKendaraanList(jdbcTemplate.query("select * from tbl_kendaraan c, tbl_jadwal_header p where " +
                        "c.idKendaraan =?",
                preparedStatement -> preparedStatement.setString(1, jadwal.getIdKendaraan()),
                (rs, rowNum) ->
                        new Kendaraan(
                                rs.getString("idKendaraan"),
                                rs.getString("platKendaraan")
                        )).get(0));
        jadwal.setDaftarHargaList((jdbcTemplate.query("select * from tbl_daftar_harga c, tbl_jadwal_header p where " +
                        "c.idDaftarHarga =?",
                preparedStatement -> preparedStatement.setString(1, jadwal.getIdPaket()),
                (rs, rowNum) ->
                        new DaftarHarga(
                                rs.getString("idDaftarHarga"),
                                rs.getString("namaPaket"),
                                rs.getString("hargaPaket"),
                                rs.getInt("status")

                        )
        )).get(0));
        return jadwal;
    }

    public List<Jadwal> findByIdPaket(String idPaket) {
        List<Jadwal> jadwalList;
        jadwalList = jdbcTemplate.query("Select * FROM tbl_jadwal_header where idPaket =?",
                new Object[]{idPaket},
                (rs, rowNum) ->
                        new Jadwal(
                                rs.getString("idJadwal"),
                                rs.getString("idKurir"),
                                rs.getString("idKendaraan"),
                                rs.getString("idPaket"),
                                rs.getInt("qty"),
                                rs.getInt("kg"),
                                rs.getInt("harga"),
                                rs.getInt("total")
                        )
        );
        for (Jadwal ch : jadwalList) {
            ch.setKurirList(jdbcTemplate.query("select * from tbl_kurir c, tbl_jadwal_header p where " +
                            "c.idKurir =?",
                    preparedStatement -> preparedStatement.setString(1, ch.getIdKurir()),
                    (rs, rowNum) ->
                            new Kurir(
                                    rs.getString("idKurir"),
                                    rs.getString("idKendaraan"),
                                    rs.getString("noKtp"),
                                    rs.getString("namaKurir"),
                                    rs.getString("handphone"),
                                    rs.getString("jenisKelamin")
                            )
            ).get(0));
            ch.getKurirList().setKendaraanList(
                    this.jdbcTemplate.query("SELECT k.idKendaraan, k.platKendaraan FROM tbl_kendaraan k, tbl_jadwal_header t WHERE k.idKendaraan = t.idKendaraan AND t.idKendaraan = ?",
                            preparedStatement -> preparedStatement.setString(1, ch.getIdKendaraan()),
                            (rs, rowNum) ->
                                    new Kendaraan(
                                            rs.getString("idKendaraan"),
                                            rs.getString("platKendaraan")
                                    )).get(0));
        }
        for (Jadwal jd : jadwalList) {
            jd.setKendaraanList(jdbcTemplate.query("select * from tbl_kendaraan c, tbl_jadwal_header p where " +
                            "c.idKendaraan =?",
                    preparedStatement -> preparedStatement.setString(1, jd.getIdKendaraan()),
                    (rs, rowNum) ->
                            new Kendaraan(
                                    rs.getString("idKendaraan"),
                                    rs.getString("platKendaraan")
                            )).get(0));
        }
        for (Jadwal ch : jadwalList) {
            ch.setDaftarHargaList((jdbcTemplate.query("select * from tbl_daftar_harga c, tbl_jadwal_header p where " +
                            "c.idDaftarHarga =?",
                    preparedStatement -> preparedStatement.setString(1, ch.getIdPaket()),
                    (rs, rowNum) ->
                            new DaftarHarga(
                                    rs.getString("idDaftarHarga"),
                                    rs.getString("namaPaket"),
                                    rs.getString("hargaPaket"),
                                    rs.getInt("status")
                            )
            )).get(0));
        }

        return jadwalList;
    }

    public List<Jadwal> findByIdKurir(String idKurir) {
        List<Jadwal> jadwalList;
        jadwalList = jdbcTemplate.query("Select * FROM tbl_jadwal_header where idKurir =?",
                new Object[]{idKurir},
                (rs, rowNum) ->
                        new Jadwal(
                                rs.getString("idJadwal"),
                                rs.getString("idKurir"),
                                rs.getString("idKendaraan"),
                                rs.getString("idPaket"),
                                rs.getInt("qty"),
                                rs.getInt("kg"),
                                rs.getInt("harga"),
                                rs.getInt("total")
                        )
        );
        for (Jadwal ch : jadwalList) {
            ch.setKurirList(jdbcTemplate.query("select * from tbl_kurir c, tbl_jadwal_header p where " +
                            "c.idKurir =?",
                    preparedStatement -> preparedStatement.setString(1, ch.getIdKurir()),
                    (rs, rowNum) ->
                            new Kurir(
                                    rs.getString("idKurir"),
                                    rs.getString("idKendaraan"),
                                    rs.getString("noKtp"),
                                    rs.getString("namaKurir"),
                                    rs.getString("handphone"),
                                    rs.getString("jenisKelamin")
                            )
            ).get(0));
            ch.getKurirList().setKendaraanList(
                    this.jdbcTemplate.query("SELECT k.idKendaraan, k.platKendaraan FROM tbl_kendaraan k, tbl_jadwal_header t WHERE k.idKendaraan = t.idKendaraan AND t.idKendaraan = ?",
                            preparedStatement -> preparedStatement.setString(1, ch.getIdKendaraan()),
                            (rs, rowNum) ->
                                    new Kendaraan(
                                            rs.getString("idKendaraan"),
                                            rs.getString("platKendaraan")
                                    )).get(0));
        }
        for (Jadwal jd : jadwalList) {
            jd.setKendaraanList(jdbcTemplate.query("select * from tbl_kendaraan c, tbl_jadwal_header p where " +
                            "c.idKendaraan =?",
                    preparedStatement -> preparedStatement.setString(1, jd.getIdKendaraan()),
                    (rs, rowNum) ->
                            new Kendaraan(
                                    rs.getString("idKendaraan"),
                                    rs.getString("platKendaraan")
                            )).get(0));
        }
        for (Jadwal ch : jadwalList) {
            ch.setDaftarHargaList((jdbcTemplate.query("select * from tbl_daftar_harga c, tbl_jadwal_header p where " +
                            "c.idDaftarHarga =?",
                    preparedStatement -> preparedStatement.setString(1, ch.getIdPaket()),
                    (rs, rowNum) ->
                            new DaftarHarga(
                                    rs.getString("idDaftarHarga"),
                                    rs.getString("namaPaket"),
                                    rs.getString("hargaPaket"),
                                    rs.getInt("status")
                            )
            )).get(0));
        }

        return jadwalList;
    }

    public void deleteJadwalById(String idJadwal) {
        jdbcTemplate.execute(" DELETE FROM tbl_jadwal_header WHERE idJadwal='" + idJadwal + "'");
    }

    @Override
    public List<Jadwal> findWithPaging(int page, int limit) {
        int numPages;
        numPages = jdbcTemplate.query("SELECT COUNT(*) as count FROM tbl_jadwal_header",
                (rs, rowNum) -> rs.getInt("count")).get(0);

        // validate page
        if (page < 1) page = 1;
        if (page > numPages) page = numPages;

        int start = (page - 1) * limit;

        System.out.println("start :" + start);
        System.out.println("limit :" + limit);
        List<Jadwal> jadwalList =
                jdbcTemplate.query("SELECT * FROM tbl_jadwal_header LIMIT " + start + "," + limit + ";",
                        (rs, rowNum) ->
                                new Jadwal(
                                        rs.getString("idJadwal"),
                                        rs.getString("idKurir"),
                                        rs.getString("idKendaraan"),
                                        rs.getString("idPaket"),
                                        rs.getInt("qty"),
                                        rs.getInt("kg"),
                                        rs.getInt("harga"),
                                        rs.getInt("total")
                                )
                );
        for (Jadwal ch : jadwalList) {
            ch.setKurirList(jdbcTemplate.query("select * from tbl_kurir c, tbl_jadwal_header p where " +
                            "c.idKurir =?",
                    preparedStatement -> preparedStatement.setString(1, ch.getIdKurir()),
                    (rs, rowNum) ->
                            new Kurir(
                                    rs.getString("idKurir"),
                                    rs.getString("idKendaraan"),
                                    rs.getString("noKtp"),
                                    rs.getString("namaKurir"),
                                    rs.getString("handphone"),
                                    rs.getString("jenisKelamin")
                            )
            ).get(0));
            ch.getKurirList().setKendaraanList(
                    this.jdbcTemplate.query("SELECT k.idKendaraan, k.platKendaraan FROM tbl_kendaraan k, tbl_jadwal_header t WHERE k.idKendaraan = t.idKendaraan AND t.idKendaraan = ?",
                            preparedStatement -> preparedStatement.setString(1, ch.getIdKendaraan()),
                            (rs, rowNum) ->
                                    new Kendaraan(
                                            rs.getString("idKendaraan"),
                                            rs.getString("platKendaraan")
                                    )).get(0));
        }
        for (Jadwal jd : jadwalList) {
            jd.setKendaraanList(jdbcTemplate.query("select * from tbl_kendaraan c, tbl_jadwal_header p where " +
                            "c.idKendaraan =?",
                    preparedStatement -> preparedStatement.setString(1, jd.getIdKendaraan()),
                    (rs, rowNum) ->
                            new Kendaraan(
                                    rs.getString("idKendaraan"),
                                    rs.getString("platKendaraan")
                            )).get(0));
        }
        for (Jadwal ch : jadwalList) {
            ch.setDaftarHargaList((jdbcTemplate.query("select * from tbl_daftar_harga c, tbl_jadwal_header p where " +
                            "c.idDaftarHarga =?",
                    preparedStatement -> preparedStatement.setString(1, ch.getIdPaket()),
                    (rs, rowNum) ->
                            new DaftarHarga(
                                    rs.getString("idDaftarHarga"),
                                    rs.getString("namaPaket"),
                                    rs.getString("hargaPaket"),
                                    rs.getInt("status")
                            )
            )).get(0));
        }
        return jadwalList;
    }
}

