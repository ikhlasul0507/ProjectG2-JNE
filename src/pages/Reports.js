import React from 'react';
import MasterKurir from './MasterKurir'
import MasterKendaraan from './MasterKendaraan'
import MasterDaftarHarga from './MasterDaftarHarga'

export const Reports = () => {
  return (
    <div className='reports'>
      <h1>Master Data</h1>
    </div>
  );
};

export const ReportsOne = () => {
  return (
      <MasterKurir/>
  );
};

export const ReportsTwo = () => {
  return (
    <div className='reports'>
      <MasterDaftarHarga/>
    </div>
  );
};

export const ReportsThree = () => {
  return (
    <div className='reports'>
      <MasterKendaraan/>
    </div>
  );
};