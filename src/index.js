import React from 'react';
import ReactDOM from 'react-dom';
import Proses from './proses';
import {Provider} from "react-redux"
import { store, persistor } from "./reducers/store";
import { PersistGate } from 'redux-persist/integration/react';

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        <Proses />
      </PersistGate>
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);
