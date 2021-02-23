
import './App.css';
import Sidebar from "./componens/Sidebar"
import {BrowserRouter as Router, Switch, Route, useHistory} from 'react-router-dom';
import { Reports, ReportsOne, ReportsTwo, ReportsThree } from './pages/Reports';
import Jadwal from './pages/Jadwal'
import Overview from './pages/Overview';
import Keluar from './pages/logout';


function Data() {
  return (
      <Router>
      <Sidebar/>
      <Switch>
        <Route path='/' exact component={Overview}/>
        <Route path='/beranda' exact component={Overview}/>
        <Route path='/reports' exact component={Reports} />
        <Route path='/reports/reports1' exact component={ReportsOne} />
        <Route path='/reports/reports2' exact component={ReportsTwo} />
        <Route path='/reports/reports3' exact component={ReportsThree} />
        <Route path='/jadwal' exact component={Jadwal} />
        <Route path='/logout' exact component={props => <Keluar {...props}/>}/>
      </Switch>
      </Router>
  );
}

export default Data;