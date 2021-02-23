import React, { Component } from 'react'
import axios from 'axios'

class MasterDaftarHarga extends Component {
    constructor(props) {
        super(props);
        this.state = {
            DaftarHarga: [],
            idDaftarHarga: "",
            namaPaket: "",
            hargaPaket: "",
            status: 0,
            isUpdate: false,
            isLoaded: false,
            paging: 1,
            statusPrev: "page-item disabled",
            statusNext: "page-item",
            url: "http://localhost:8080/jne/harga/"
        }
    }
    componentDidMount() {
        this.ambilApi()
    }
    setValue = el => {
        console.log(el.target.value);
        this.setState({
            [el.target.name]: el.target.value,
        })
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
                        let DaftarHarga = [];
                        DaftarHarga.push(res.data)
                        console.log("data cari :", DaftarHarga)
                        this.setState({
                            DaftarHarga: DaftarHarga
                        })

                    })
                    .catch((e) => {
                        console.log(e);
                        alert("Plat Kendaraan  Tidak Ada!!");
                    });
            } else if (this.valueSelect === "id") {
                axios.get(this.state.url + nilai)
                    .then((res) => {
                        console.log("data", nilai)
                        let DaftarHarga = [];
                        DaftarHarga.push(res.data)
                        console.log("data cari :", DaftarHarga)
                        this.setState({
                            DaftarHarga: DaftarHarga
                        })

                    })
                    .catch((e) => {
                        console.log(e);
                        alert("Masukan ID Spesifik !!");
                    });
            }
        }
    }
    ambilApi = () => {
        axios.get(this.state.url + "paging/?page=" + this.state.paging + "&limit=5")
            .then((res) => {
                console.log("data", res.data)
                this.setState({
                    DaftarHarga: res.data,
                    isLoaded: true
                })
            })

    }
    hapusApi = (idDaftarHarga) => {
        console.log(idDaftarHarga)
        axios.delete(this.state.url + idDaftarHarga)

            .then((res) => {
                alert("Berhasil Di Hapus !")
                this.ambilApi();
            })
            .catch(() => {
                alert("Gagal Di Hapus !")
            })
    }
    editApi = (idDaftarHarga) => {
        console.log(idDaftarHarga);
        axios.get(this.state.url + idDaftarHarga)
            .then((res) => {
                console.log("data", res.data)
                this.setState({
                    idDaftarHarga: res.data.idDaftarHarga,
                    namaPaket: res.data.namaPaket,
                    hargaPaket: res.data.hargaPaket,
                    status: res.data.status,
                    isUpdate: true
                })
            })

    }
    editToApi = () => {
        axios.put(this.state.url + this.state.idDaftarHarga, {
            idDaftarHarga: this.state.idDaftarHarga,
            namaPaket: this.state.namaPaket,
            hargaPaket: this.state.hargaPaket,
            status: this.state.status
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
        axios.post(this.state.url, {
            namaPaket: this.state.namaPaket,
            hargaPaket: this.state.hargaPaket,
            status: this.state.status
        })
            .then((res) => {
                console.log(res)
                this.ambilApi();
                alert("Berhasil Di Simpan !")
            })
            .catch(() => {
                alert("Gagal Di Simpan !")
            })
    }
    clearForm = () => {
        this.setState({
            namaPaket: "",
            hargaPaket: "",
            status:""
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
        const { idDaftarHarga,namaPaket,hargaPaket ,status} = this.state
        let {
            isLoaded,
        } = this.state
        console.log(!isLoaded)
        if (!isLoaded) {
            console.log(!isLoaded)
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
                            <option value="nama">Nama</option>
                        </select>
                        <input type="text" className="form-control" name="cari" onChange={el => this.onChangeSearch(el)} placeholder="Pencarian..." aria-label="Recipient's username" aria-describedby="button-addon2" />

                    </div>
                    <div className="table-responsive">
                        Page : {this.state.paging}
                        <table className="table mt-3" border='1'>
                            <thead>
                                <tr>
                                    <th scope="col">ID Daftar Harga</th>
                                    <th scope="col">Nama Paket</th>
                                    <th scope="col">Harga Paket</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Aksi</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.DaftarHarga.map(
                                        (Item, idx) =>
                                            <tr key={idx}>
                                                <td className="idJadwal">{Item.idDaftarHarga}</td>
                                                <td>{Item.namaPaket}</td>
                                                <td>{Item.hargaPaket}</td>
                                                <td>{Item.status ===0 ? <span class="badge bg-danger text-dark">Pending</span> : <span className="badge bg-warning text-dark">On Progress</span>}</td>
                                                <td>
                                                    <button className="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick={() => this.editApi(Item.idDaftarHarga)}>Edit</button>
                                                    <button className="btn btn-danger ml-3" onClick={() => { if (window.confirm('Yakin Mau Delete ?')) { this.hapusApi(Item.idDaftarHarga) } }} >Hapus</button>
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
                                    <h5 className="modal-title" id="exampleModalLabel">Data Daftar Paket</h5>
                                    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div className="modal-body">
                                    <div className="mb-3">
                                        <label className="form-label">Nama Paket</label>
                                        <input type="text" className="form-control" name="namaPaket" value={namaPaket} onChange={this.setValue} placeholder="Nama Paket" />
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">Harga Paket</label>
                                        <input type="text" className="form-control" name="hargaPaket" value={hargaPaket} onChange={this.setValue} placeholder="Harga Paket" />
                                        <input type="hidden" className="form-control" name="status" value={status} onChange={this.setValue} placeholder="Harga Paket" />
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

export default MasterDaftarHarga;