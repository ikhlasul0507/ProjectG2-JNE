import React from 'react';
import ReactDOM from 'react-dom';
import Proses from './proses';
import {Provider} from "react-redux"
import store from "./reducers"
ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
    <Proses />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);
