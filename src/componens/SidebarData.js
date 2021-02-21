import React from 'react';
import * as FaIcons from 'react-icons/fa'
import * as AiIcons from 'react-icons/ai'
import * as IoIcons from 'react-icons/io'
import * as RiIcons from 'react-icons/ri'

export const SidebarData = [
    {
        title: 'Beranda',
        path: '/beranda',
        icon: <FaIcons.FaHome />
      },
    {
      title: 'Master',
      path: '/reports',
      icon: <IoIcons.IoIosPaper />,
      iconClosed: <RiIcons.RiArrowDownSFill />,
      iconOpened: <RiIcons.RiArrowUpSFill />,
  
      subNav: [
        {
          title: 'Kurir',
          path: '/reports/reports1',
          icon: <IoIcons.IoIosPaper />,
          cName: 'sub-nav'
        },
        {
          title: 'Daftar Harga',
          path: '/reports/reports2',
          icon: <IoIcons.IoIosPaper />,
          cName: 'sub-nav'
        },
        {
          title: 'Master Kendaraan',
          path: '/reports/reports3',
          icon: <IoIcons.IoIosPaper />
        }
      ]
    },
    {
      title: 'Jadwal',
      path: '/jadwal',
      icon: <IoIcons.IoMdCalendar />
    },
    {
      title: 'Keluar',
      path: '/logout',
      icon: <IoIcons.IoMdExit/>
    }
  ];