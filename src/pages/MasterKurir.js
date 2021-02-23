import React, { Component } from 'react'
import axios from 'axios'

class MasterKurir extends Component {
    constructor(props) {
        super(props);
        this.state = {
            kurir: [],
            idKurir: "",
            idKendaraan: "",
            Kendaraan: [],
            noKtp: "",
            namaKurir: "",
            handphone: "",
            jenisKelamin: "",
            isUpdate: false,
            isLoaded: false,
            paging: 1,
            statusPrev: "page-item disabled",
            statusNext: "page-item",
            url: "http://localhost:8080/jne/kurir/",
            urlKendaraan : "http://localhost:8080/jne/kendaraan/"
        }
    }
    componentDidMount() {
        this.ambilApi()
        this.ambilApiKendaraan()
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
                        let kurir = [];
                        kurir.push(res.data)
                        console.log("data cari :", kurir)
                        this.setState({
                            kurir: kurir
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
                        let kurir = [];
                        kurir.push(res.data)
                        console.log("data cari :", kurir)
                        this.setState({
                            kurir: kurir
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
                    kendaraan: res.data,
                    isLoaded: true
                })
            })

    }
    ambilApi = () => {
        axios.get(this.state.url + "paging/?page=" + this.state.paging + "&limit=5")
            .then((res) => {
                console.log("data", res.data)
                this.setState({
                    kurir: res.data,
                    isLoaded: true
                })
            })

    }
    hapusApi = (idKurir) => {
        console.log(idKurir)
        axios.delete(this.state.url + idKurir)

            .then((res) => {
                alert("Berhasil Di Hapus !")
                this.ambilApi();
            })
            .catch(() => {
                alert("Gagal Di Hapus !")
            })
    }
    editApi = (idKurir) => {
        console.log(idKurir);
        axios.get(this.state.url + idKurir)
            .then((res) => {
                console.log("data", res.data)
                this.setState({
                    idKurir: res.data.idKurir,
                    idKendaraan: res.data.idKendaraan,
                    noKtp: res.data.noKtp,
                    namaKurir: res.data.namaKurir,
                    handphone: res.data.handphone,
                    jenisKelamin: res.data.jenisKelamin,
                    isUpdate: true
                })
            })

    }
    editToApi = () => {
        axios.put(this.state.url + this.state.idKurir, {
            idKurir: this.state.idKurir,
            idKendaraan: this.state.idKendaraan,
            noKtp: this.state.noKtp,
            namaKurir: this.state.namaKurir,
            handphone: this.state.handphone,
            jenisKelamin: this.state.jenisKelamin,
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
            idKendaraan: this.state.idKendaraan,
            noKtp: this.state.noKtp,
            namaKurir: this.state.namaKurir,
            handphone: this.state.handphone,
            jenisKelamin: this.state.jenisKelamin
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
            idKendaraan: "",
            noKtp: "",
            namaKurir: "",
            handphone: "",
            jenisKelamin: ""
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
        const { idKendaraan, noKtp, namaKurir, handphone, jenisKelamin } = this.state
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
                                    <th scope="col">ID Kurir</th>
                                    <th scope="col">Plat Kendaraan</th>
                                    <th scope="col">No KTP</th>
                                    <th scope="col">Nama Kurir</th>
                                    <th scope="col">Handphone</th>
                                    <th scope="col">Jenis Kelamin</th>
                                    <th scope="col">Aksi</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.kurir.map(
                                        (Item, idx) =>
                                            <tr key={idx}>
                                                <td className="idJadwal">{Item.idKurir}</td>
                                                <td>{Item.kendaraanList.platKendaraan}</td>
                                                <td>{Item.noKtp}</td>
                                                <td>{Item.namaKurir}</td>
                                                <td>{Item.handphone}</td>
                                                <td>{Item.jenisKelamin}</td>
                                                <td>
                                                    <button className="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick={() => this.editApi(Item.idKurir)}>Edit</button>
                                                    <button className="btn btn-danger ml-3" onClick={() => { if (window.confirm('Yakin Mau Delete ?')) { this.hapusApi(Item.idKurir) } }} >Hapus</button>
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
                                    <h5 className="modal-title" id="exampleModalLabel">Data Kurir</h5>
                                    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div className="modal-body">
                                    <div className="mb-3">
                                        <label className="form-label">Plat Kendaraan</label>
                                        <select className="form-control" aria-label="Default select example" name="idKendaraan" value={idKendaraan} onChange={this.setValue}>
                                            <option defaultValue="0">--Pilih--</option>
                                            {
                                                this.state.kendaraan.map(
                                                    (Item, idx) =>
                                                        <option key={idx} value={Item.idKendaraan}>{Item.platKendaraan}</option>
                                                )
                                            }
                                        </select>
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">No KTP</label>
                                        <input type="number" className="form-control" name="noKtp" value={noKtp} onChange={this.setValue} placeholder="No KTP" />
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">Nama Kurir</label>
                                        <input type="text" className="form-control" name="namaKurir" value={namaKurir} onChange={this.setValue} placeholder="Nama Kurir" />
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">Handphone</label>
                                        <input type="number" className="form-control" name="handphone" value={handphone} onChange={this.setValue} placeholder="Handphone" />
                                    </div>
                                    <div className="mb-3">
                                        <label className="form-label">Jenis Kelamin</label>
                                        <select className="form-control" aria-label="Default select example" name="jenisKelamin" value={jenisKelamin} onChange={this.setValue}>
                                            <option defaultValue="0">--Pilih--</option>
                                            <option value="L">Laki-Laki</option>
                                            <option value="P">Perempuan</option>
                                        </select>
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

export default MasterKurir;