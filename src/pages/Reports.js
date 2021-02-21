import React from 'react';
import MasterKurir from './MasterKurir'
import MasterKendaraan from './MasterKendaraan'
import MasterDaftarHarga from './MasterDaftarHarga'
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';

export const Reports = () => {
  return (
    <div className='reports'>
      <div className="card mt-5">
        <div className="card-header idUserProps">
          Master Data
  </div>
        <img src="https://4.bp.blogspot.com/-sb-njHG5ovE/Wg0ZPngqWRI/AAAAAAAAE9s/C3KfcO_aS3YvMBTaj7wNx3VkqSArhA_4ACLcBGAs/s1600/JNE.jpg" className="card-img-top" alt="..." />

        <ul className="list-group list-group-flush">
          {/* <li className="list-group-item"><FaIcons.FaUsers />___{props.dataLogin.username}</li>
          <li className="list-group-item"><FaIcons.FaMailBulk />___{props.dataLogin.email}</li>
          <li className="list-group-item idUserProps">{props.dataLogin.idUser}</li> */}
        </ul>
      </div>
    </div>
  );
};

export const ReportsOne = () => {
  return (
    <MasterKurir />
  );
};

export const ReportsTwo = () => {
  return (
    <div className='reports'>
      <MasterDaftarHarga />
    </div>
  );
};

export const ReportsThree = () => {
  return (
    <div className='reports'>
      <MasterKendaraan />
    </div>
  );
};