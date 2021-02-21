import React, { Component } from 'react'
import axios from 'axios'

class Jadwal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            jadwal: [],
            idKurir: "",
            idDaftarHarga: "",
            idKendaraan: "",
            Kendaraan: [],
            Kurir: [],
            Paket: [],
            noKtp: "",
            qty: "",
            kg: "",
            harga: "",
            total: "",
            isUpdate: false,
            isLoaded: false,
            paging: 1,
            statusPrev: "page-item disabled",
            statusNext: "page-item",
            url: "http://localhost:8080/jne/jadwal/",
            urlKendaraan: "http://localhost:8080/jne/kendaraan/",
            urlKurir: "http://localhost:8080/jne/kurir/",
            urlPaket: "http://localhost:8080/jne/harga/"
        }
    }
    componentDidMount() {
        this.ambilApi()
        this.ambilApiKendaraan()
        this.ambilApiKurir()
        this.ambilApiPaket()
    }
    setValue = el => {
        console.log(el.target);
        this.setState({
            [el.target.name]: el.target.value,
        })
        if(el.target.name === "idDaftarHarga"){
            console.log(el.target.value)
            let data = el.target.value
            var cariIndex = this.state.Paket.findIndex(function(data1){
                return data1.idDaftarHarga===data
            });
            console.log(this.state.Paket[cariIndex].hargaPaket)
            console.log(cariIndex)
            this.setState({
                harga : this.state.Paket[cariIndex].hargaPaket
            })
            console.log("harga :", this.state.harga)
        }
    }
    onChangeSelect = el => {
        this.valueSelect = el.target.value;
        console.log(el.target.value)
    }
    onChangeSearch = el => {
        const nilai = el.target.value;
        console.log("nilai :", this.valueSelect)
        if (this.valueSelect === undefined) {
            alert("Pilih Jenis Pencarian !")
        } else {
            if (nilai === "") {
                this.ambilApi()

            } else if (this.valueSelect === "nama") {
                axios.get(this.state.url + "/nama/" + nilai)
                    .then((res) => {
                        console.log("data", nilai)
                        let jadwal = [];
                        jadwal.push(res.data)
                        console.log("data cari :", jadwal)
                        this.setState({
                            jadwal: jadwal
                        })

                    })
                    .catch((e) => {
                        console.log(e);
                        alert("ID  Tidak Ada!!");
                    });
            } else if (this.valueSelect === "id") {
                axios.get(this.state.url + nilai)
                    .then((res) => {
                        console.log("data", nilai)
                        let jadwal = [];
                        jadwal.push(res.data)
                        console.log("data cari :", jadwal)
                        this.setState({
                            jadwal: jadwal
                        })

                    })
                    .catch((e) => {
                        console.log(e);
                        alert("Masukan ID Spesifik !!");
                    });
            }
        }
    }
    ambilApiKendaraan = () => {
        axios.get(this.state.urlKendaraan)
            .then((res) => {
                console.log("data", res.data)
                this.setState({
                    Kendaraan: res.data,
                    isLoaded: true
                })
            })
            .catch(() => {
                alert("Gagal Mengambil Data !")
            })

    }
    ambilApiKurir = () => {
        axios.get(this.state.urlKurir)
            .then((res) => {
                console.log("data", res.data)
                this.setState({
                    Kurir: res.data,
                    isLoaded: true
                })
            })
            .catch(() => {
                alert("Gagal Mengambil Data !")
            })

    }
    ambilApiPaket = () => {
        axios.get(this.state.urlPaket)
            .then((res) => {
                console.log("data", res.data)
                this.setState({
                    Paket: res.data,
                    isLoaded: true
                })
            })
            .catch(() => {
                alert("Gagal Mengambil Data !")
            })

    }
    ambilApi = () => {
        axios.get(this.state.url + "paging/?page=" + this.state.paging + "&limit=5")
            .then((res) => {
                console.log("data", res.data)
                this.setState({
                    jadwal: res.data,
                    isLoaded: true
                })
            })
            .catch(() => {
                alert("Gagal Mengambil Data !")
            })

    }
    hapusApi = (idJadwal) => {
        console.log(idJadwal)
        axios.delete(this.state.url + idJadwal)

            .then((res) => {
                alert("Berhasil Di Hapus !")
                this.ambilApi();
            })
            .catch(() => {
                alert("Gagal Di Hapus !")
            })
    }
    editApi = (idJadwal) => {
        console.log(idJadwal);
        axios.get(this.state.url + idJadwal)
            .then((res) => {
                console.log("data", res.data)
                this.setState({
                    idKurir: res.data.idKurir,
                    idKendaraan: res.data.idKendaraan,
                    idDaftarHarga: res.data.idDaftarHarga,
                    qty: res.data.qty,
                    kg: res.data.kg,
                    harga: res.data.harga,
                    total: res.data.total,
                    isUpdate: true
                })
            })

    }
    editToApi = () => {
        axios.put(this.state.url + this.state.idJadwal, {
            idKurir: this.state.idKurir,
            idKendaraan: this.state.idKendaraan,
            idDaftarHarga: this.state.idDaftarHarga,
            qty: this.state.qty,
            kg: this.state.kg,
            harga: this.state.harga,
            total: this.state.total
        })
            .then((res) => {
                this.setState({
                    isUpdate: false
                })
                alert("Data Berhasil Di Edit !")
                console.log("update ", this.state.isUpdate)
                this.ambilApi();
            })
            .catch(() => {
                alert("Gagal Di Edit !")
            })

    }
    saveApi = () => {
        if (this.state.isUpdate) {
            this.editToApi();
            this.clearForm();
        } else {
            this.saveToApi();
            this.clearForm();
        }

    }
    saveToApi = () => {
        console.log("Id Kurir :",this.state.idKurir);
        console.log("Id Kendaraan :",this.state.idKendaraan);
        console.log("Id Daftar Harga :",this.state.idDaftarHarga);
        console.log("Qty :",this.state.qty);
        console.log("kg",this.state.kg);
        console.log("harga",this.state.harga);
        axios.post(this.state.url, {
            idKurir: this.state.idKurir,
            idKendaraan: this.state.idKendaraan,
            idPaket: this.state.idDaftarHarga,
            qty: this.state.qty,
            kg: this.state.kg,
            harga: this.state.harga
        })
            .then((res) => {
                console.log(res)
                this.ambilApi();
                this.ambilApiPaket();
                alert("Berhasil Di Simpan !")
            })
            .catch((res) => {
                console.log(res)
                alert("Gagal Di Simpan !")
            })
    }
    clearForm = () => {
        this.setState({
            idKurir: "",
            idKendaraan: "",
            idDaftarHarga: "",
            qty: "",
            kg: "",
            harga: "",
            total: ""
        })
    }
    prev = () => {
        const { paging } = this.state
        let prev = paging - 1
        if (paging > 1) {
            this.setState({
                ...this.state,
                paging: prev--,
                statusNext: "page-item"
            })
            this.ambilApi();
            console.log(paging)
            console.log(this.state.statusPrev)
        } else {
            this.setState({
                paging: 1,
                statusPrev: "page-item disabled"
            })
        }
    }
    next = () => {
        const { paging } = this.state
        let next = paging + 1
        this.setState({
            paging: next++,
            statusPrev: "page-item"
        })
        this.ambilApi();
        console.log(paging)
        console.log(this.state.statusPrev)
    }
    render() {
        let { idKendaraan, idKurir, idDaftarHarga, qty, kg, harga, total } = this.state
        const total1 = (qty * kg * harga) + ((qty * kg * harga) * 0.10)
        this.state.total = total1
        console.log("total state :", this.state.total)
        let {
            isLoaded,
        } = this.state
        if (!isLoaded) {
            return <div className="loader">Loading Data...</div>
        } else {
            return (
                <div className='container'>
                    <button type="button" className="btn btn-primary mt-3" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Tambah Data
                </button>
                    <div className="input-group mb-3 mt-3">
                        <select className="form-select" aria-label="Default select example" onChange={this.onChangeSelect}>
                            <option defaultValue="0">--Pilih--</option>
                            <option value="id">ID</option>
                            {/* <option value="nama">Nama</option> */}
                        </select>
                        <input type="text" className="form-control" name="cari" onChange={el => this.onChangeSearch(el)} placeholder="Pencarian..." aria-label="Recipient's username" aria-describedby="button-addon2" />

                    </div>
                    <div className="table-responsive">
                        Page : {this.state.paging}
                        <table className="table mt-3" border='1'>
                            <thead>
                                <tr>
                                    <th scope="col">ID Jadwal</th>
                                    <th scope="col">Plat Kendaraan</th>
                                    <th scope="col">Nama Kurir</th>
                                    <th scope="col">Handphone</th>
                                    <th scope="col">Paket</th>
                                    <th scope="col">Harga Paket</th>
                                    <th scope="col">Qty</th>
                                    <th scope="col">Berat (Kg)</th>
                                    <th scope="col">Harga (Rp)</th>
                                    <th scope="col">Total (Rp)</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Aksi</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.jadwal.map(
                                        (Item, idx) =>
                                            <tr key={idx}>
                                                <td className="idJadwal">{Item.idJadwal}</td>
                                                <td>{Item.kurirList.kendaraanList.platKendaraan}</td>
                                                <td>{Item.kurirList.namaKurir}</td>
                                                <td>{Item.kurirList.handphone}</td>
                                                <td>{Item.daftarHargaList.namaPaket}</td>
                                                <td>Rp.{Item.daftarHargaList.hargaPaket},-</td>
                                                <td>{Item.qty}</td>
                                                <td>{Item.kg}</td>
                                                <td>Rp.{Item.harga},-</td>
                                                <td>Rp.{Item.total},-</td>
                                                <td>
                                                    <button type="button" className="btn btn-warning">
                                                        Sedang Pengiriman<span className="badge bg-primary">{Item.daftarHargaList.status}</span>
                                                    </button>
                                                </td>
                                                <td>
                                                    <button className="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick={() => this.editApi(Item.idJadwal)}>Edit</button>
                                                    <button className="btn btn-danger ml-3" onClick={() => { if (window.confirm('Yakin Mau Delete ?')) { this.hapusApi(Item.idJadwal) } }} >Hapus</button>
                                                </td>
                                            </tr>
                                    )
                                }
                            </tbody>
                        </table>
                    </div>
                    <nav aria-label="...">
                        <ul className="pagination">
                            <li className={this.state.statusPrev}>
                                <a className="page-link" href="#" tabIndex="-1" aria-disabled="true" onClick={this.prev}>Previous</a>
                            </li>
                            <li className={this.state.statusNext}>
                                <a className="page-link" href="#" onClick={this.next}>Next</a>
                            </li>
                        </ul>
                    </nav>

                    <div className="modal fade" id="exampleModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="false" role="dialog" style={{ paddingRight: 17 }}>
                        <div className="modal-dialog">
                            <div className="modal-content">
                                <div className="modal-header">
                                    <h5 className="modal-title" id="exampleModalLabel">Data Jadwal</h5>
                                    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div className="modal-body">
                                    <div className="mb-3">
                                        <label className="form-label">Kurir</label>
                                        <select className="form-control" aria-label="Default select example" name="idKurir" value={idKurir} onChange={this.setValue}>
                                            <option defaultValue="0">--Pilih Kurir--</option>
                                            {
                                                this.state.Kurir.map(
                                                    (Item, idx) =>
                                                        <option key={idx} value={Item.idKurir}>{Item.namaKurir}</option>
                                                )
                                            }
                                        </select>
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">Plat Kendaraan</label>
                                        <select className="form-control" aria-label="Default select example" name="idKendaraan" value={idKendaraan} onChange={this.setValue}>
                                            <option defaultValue="0">--Pilih Kendaraan--</option>
                                            {
                                                this.state.Kendaraan.map(
                                                    (Item, idx) =>
                                                        <option key={idx} value={Item.idKendaraan}>{Item.platKendaraan}</option>
                                                )
                                            }
                                        </select>
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">Paket</label>
                                        <select className="form-control" aria-label="Default select example" name="idDaftarHarga" value={idDaftarHarga} onChange={this.setValue}>
                                            <option defaultValue="0">--Pilih Paket--</option>
                                            {
                                                this.state.Paket.map(
                                                    (Item, idx) =>
                                                        <option key={idx} value={Item.idDaftarHarga}>{Item.namaPaket}</option>
                                                )
                                            }
                                        </select>
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">Qty</label>
                                        <input type="number" className="form-control" name="qty" value={qty} onChange={this.setValue} placeholder="Qty" />
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">Kg</label>
                                        <input type="number" className="form-control" name="kg" value={kg} onChange={this.setValue} placeholder="Kg" />
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">Harga</label>
                                        <input type="number" disabled className="form-control" name="harga" value={harga} onChange={this.setValue} placeholder="Harga" />
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">Total</label>
                                        <input type="number" disabled className="form-control" name="total" value={total} onChange={this.setValue} placeholder="Total" />
                                    </div>
                                </div>
                                <div className="modal-footer">
                                    <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Tutup</button>
                                    <button type="button" className="btn btn-primary" onClick={this.saveApi}>Simpan</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            );
        }
    }
}

export default Jadwal;