import React, { Component } from 'react'
import axios from 'axios'
import DivSingleClass from "../componens/div/divSingleClass"
import Label from "../componens/label"
import Input from "../componens/input"
import H5 from "../componens/h5"
import Button from "../componens/button"
import Select from "../componens/select"
import Option from "../componens/option"
import Ul from "../componens/ul"
import Li from "../componens/li"
import A from "../componens/a"
import Nav from "../componens/nav"
import Table from "../componens/table"
import Thead from "../componens/table/thead"
import Tr from "../componens/table/tr"
import Td from "../componens/table/td"
import Th from "../componens/table/th"
import Tbody from "../componens/table/tbody"
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
                        alert("Nama  Tidak Ada!!");
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
            return <DivSingleClass className="loader">Loading Data...</DivSingleClass>
        } else {
            return (
                <DivSingleClass className='container'>
                    <button type="button" className="btn btn-primary mt-3" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Tambah Data
                    </button>
                    <DivSingleClass className="input-group mb-3 mt-3">
                        <Select className="form-select" aria-label="Default select example" onChange={this.onChangeSelect}>
                            <Option defaultValue="0">--Pilih--</Option>
                            <Option value="id">ID</Option>
                            {/* <option value="nama">Nama</option> */}
                        </Select>
                        <Input type="text" className="form-control" name="cari" onChange={el => this.onChangeSearch(el)} placeholder="Pencarian..." aria-label="Recipient's username" aria-describedby="button-addon2" />

                    </DivSingleClass>
                    <DivSingleClass className="table-responsive">
                        Page : {this.state.paging}
                        <Table className="table mt-3" border='1'>
                            <Thead>
                                <Tr>
                                    <Th scope="col">ID Jadwal</Th>
                                    <Th scope="col">Plat Kendaraan</Th>
                                    <Th scope="col">Nama Kurir</Th>
                                    <Th scope="col">Handphone</Th>
                                    <Th scope="col">Paket</Th>
                                    <Th scope="col">Harga Paket</Th>
                                    <Th scope="col">Qty</Th>
                                    <Th scope="col">Berat (Kg)</Th>
                                    <Th scope="col">Harga (Rp)</Th>
                                    <Th scope="col">Total (Rp)</Th>
                                    <Th scope="col">Status</Th>
                                    <Th scope="col">Aksi</Th>
                                </Tr>
                            </Thead>
                            <Tbody>
                                {
                                    this.state.jadwal.map(
                                        (Item, idx) =>
                                            <Tr key={idx}>
                                                <Td className="idJadwal">{Item.idJadwal}</Td>
                                                <Td>{Item.kurirList.kendaraanList.platKendaraan}</Td>
                                                <Td>{Item.kurirList.namaKurir}</Td>
                                                <Td>{Item.kurirList.handphone}</Td>
                                                <Td>{Item.daftarHargaList.namaPaket}</Td>
                                                <Td>Rp.{Item.daftarHargaList.hargaPaket},-</Td>
                                                <Td>{Item.qty}</Td>
                                                <Td>{Item.kg}</Td>
                                                <Td>Rp.{Item.harga},-</Td>
                                                <Td>Rp.{Item.total},-</Td>
                                                <Td>
                                                    {Item.daftarHargaList.status ===0 ? <span class="badge bg-danger text-dark">Pending</span> : <span className="badge bg-warning text-dark">On Progress</span>}
                                                </Td>
                                                <Td>
                                                    <button className="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick={() => this.editApi(Item.idJadwal)}>Edit</button>
                                                    <Button className="btn btn-danger ml-3" onClick={() => { if (window.confirm('Yakin Mau Delete ?')) { this.hapusApi(Item.idJadwal) } }} >Hapus</Button>
                                                </Td>
                                            </Tr>
                                    )
                                }
                            </Tbody>
                        </Table>
                    </DivSingleClass>
                    <Nav aria-label="...">
                        <Ul className="pagination">
                            <Li className={this.state.statusPrev}>
                                <A className="page-link" href="#" tabIndex="-1" aria-disabled="true" onClick={this.prev}>Previous</A>
                            </Li>
                            <Li className={this.state.statusNext}>
                                <A className="page-link" href="#" onClick={this.next}>Next</A>
                            </Li>
                        </Ul>
                    </Nav>

                    <div className="modal fade" id="exampleModal" tabIndex="-1" aria-labelledby="exampleModalLabel" aria-hidden="false" role="dialog" style={{ paddingRight: 17 }}>
                        <DivSingleClass className="modal-dialog">
                            <DivSingleClass className="modal-content">
                                <DivSingleClass className="modal-header">
                                    <H5 className="modal-title" id="exampleModalLabel">Data Jadwal</H5>
                                    <Button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></Button>
                                </DivSingleClass>
                                <DivSingleClass className="modal-body">
                                    <DivSingleClass className="mb-3">
                                        <Label className="form-label">Kurir</Label>
                                        <Select className="form-control" aria-label="Default select example" name="idKurir" value={idKurir} onChange={this.setValue}>
                                            <Option defaultValue="0">--Pilih Kurir--</Option>
                                            {
                                                this.state.Kurir.map(
                                                    (Item, idx) =>
                                                        <Option key={idx} value={Item.idKurir}>{Item.namaKurir}</Option>
                                                )
                                            }
                                        </Select>
                                    </DivSingleClass>
                                    <DivSingleClass className="mb-3">
                                        <Label className="form-label">Plat Kendaraan</Label>
                                        <Select className="form-control" aria-label="Default select example" name="idKendaraan" value={idKendaraan} onChange={this.setValue}>
                                            <Option defaultValue="0">--Pilih Kendaraan--</Option>
                                            {
                                                this.state.Kendaraan.map(
                                                    (Item, idx) =>
                                                        <Option key={idx} value={Item.idKendaraan}>{Item.platKendaraan}</Option>
                                                )
                                            }
                                        </Select>
                                    </DivSingleClass>
                                    <DivSingleClass className="mb-3">
                                        <Label className="form-label">Paket</Label>
                                        <Select className="form-control" aria-label="Default select example" name="idDaftarHarga" value={idDaftarHarga} onChange={this.setValue}>
                                            <Option defaultValue="0">--Pilih Paket--</Option>
                                            {
                                                this.state.Paket.map(
                                                    (Item, idx) =>
                                                        <Option key={idx} value={Item.idDaftarHarga}>{Item.namaPaket}</Option>
                                                )
                                            }
                                        </Select>
                                    </DivSingleClass>
                                    <DivSingleClass className="mb-3">
                                        <Label className="form-label">Qty</Label>
                                        <Input type="number" className="form-control" name="qty" value={qty} onChange={this.setValue} placeholder="Qty" />
                                    </DivSingleClass>
                                    <DivSingleClass className="mb-3">
                                        <Label className="form-label">Kg</Label>
                                        <Input type="number" className="form-control" name="kg" value={kg} onChange={this.setValue} placeholder="Kg" />
                                    </DivSingleClass>
                                    <DivSingleClass className="mb-3">
                                        <Label className="form-label">Harga</Label>
                                        <input type="number" disabled className="form-control" name="harga" value={harga} onChange={this.setValue} placeholder="Harga" />
                                    </DivSingleClass>
                                    <DivSingleClass className="mb-3">
                                        <Label className="form-label">Total</Label>
                                        <input type="number" disabled className="form-control" name="total" value={total} onChange={this.setValue} placeholder="Total" />
                                    </DivSingleClass>
                                </DivSingleClass>
                                <DivSingleClass className="modal-footer">
                                    <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Tutup</button>
                                    <Button type="button" className="btn btn-primary" onClick={this.saveApi}>Simpan</Button>
                                </DivSingleClass>
                            </DivSingleClass>
                        </DivSingleClass>
                    </div>
                </DivSingleClass>
            );
        }
    }
}

export default Jadwal;