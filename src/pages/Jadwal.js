import React from 'react';

const Jadwal = () => {
  return (
    <div class='container'>
    <button type="button" class="btn btn-primary mt-3" data-bs-toggle="modal" data-bs-target="#exampleModal">
        Tambah Data
    </button>
    <div class="input-group mb-3 mt-3">
        <input type="text" class="form-control" placeholder="Pencarian..." aria-label="Recipient's username" aria-describedby="button-addon2" />
        <button class="btn btn-primary" type="button" id="button-addon2">Cari</button>
    </div>
    <div class="table-responsive">
    <table class="table mt-3" border='1'>
        <thead>
            <tr>
                <th scope="col">ID Kurir</th>
                <th scope="col">No KTP</th>
                <th scope="col">Nama Kurir</th>
                <th scope="col">Plat Kendaraan</th>
                <th scope="col">Handphone</th>
                <th scope="col">Jenis Kelamin</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th scope="row">1</th>
                <td>Mark</td>
                <td>Otto</td>
                <td>@mdo</td>
                <td>@mdo</td>
                <td>@mdo</td>
            </tr>
            <tr>
                <th scope="row">2</th>
                <td>Jacob</td>
                <td>Thornton</td>
                <td>Thornton</td>
                <td>Thornton</td>
                <td>@fat</td>
            </tr>
        </tbody>
    </table>
    </div>
    <nav aria-label="...">
        <ul class="pagination">
            <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active" aria-current="page">
                <a class="page-link" href="#">2</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
                <a class="page-link" href="#">Next</a>
            </li>
        </ul>
    </nav>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</div>
  );
};

export default Jadwal;