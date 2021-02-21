
import './App.css';
import App from './App'
import Keluar from './pages/keluar';
import Login from './pages/login';
import AuthFn from "./action/auth"
import {connect} from "react-redux"
import React, { Component } from 'react'
import axios from 'axios'

class Proses extends Component {
  constructor(props) {
    super(props);
    this.state = {
      users: [],
      url: "http://localhost:8080/jne/user/"
    }
  }
  ambilApi = () => {
    axios.get(this.state.url)
      .then((res) => {
        console.log("data", res.data)
        this.setState({
          users: res.data
        })
      })
      .catch(() => {
        alert("Gagal Mengambil Data !")
      })

  }
  doLogin = login =>{
    alert("Login")
    
  }
  render() {
    return (
      <>
        <Login
          doLogin={this.doLogin}
        />
        {/* <App/> */}
        {/* <Keluar/>  */}
      </>
    );
  }
}

const mapStateToProps = state =>({
  checkLogin: state.AReducer.isLogin,
})

const mapDispatchToProps = dispatch =>{
  return{
    submitLogin: (data) => dispatch(AuthFn(data)),
    keluar: () => dispatch({ type: "LOGOUT_SUCCESS" }),
    updateUser: payload => dispatch({ type: "SET_DATA", payload }),
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Proses);