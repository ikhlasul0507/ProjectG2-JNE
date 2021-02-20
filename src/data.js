
import './App.css';
import Sidebar from "./componens/Sidebar"
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import { Reports, ReportsOne, ReportsTwo, ReportsThree } from './pages/Reports';
import Jadwal from './pages/Jadwal'
import Overview from './pages/Overview';


function Data() {
  return (
      <Router>
      <Sidebar/>
      <Switch>
        <Route path='/beranda' exact component={Overview}/>
        <Route path='/reports' exact component={Reports} />
        <Route path='/reports/reports1' exact component={ReportsOne} />
        <Route path='/reports/reports2' exact component={ReportsTwo} />
        <Route path='/reports/reports3' exact component={ReportsThree} />
        <Route path='/jadwal' exact component={Jadwal} />
      </Switch>
      </Router>
  );
}

export default Data;