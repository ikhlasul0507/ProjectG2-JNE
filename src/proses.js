
import './App.css';
import App from './App'
import Keluar from './pages/keluar';
import Login from './pages/login';
import { connect } from "react-redux"
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

  componentDidMount() {
    this.ambilApi()
  }
  ambilApi = () => {
    axios.get(this.state.url)
      .then((res) => {
        console.log(res.data)
        this.setState({
          users: res.data
        })
      })
      .catch(() => {
        alert("Gagal Mengambil Data !")
      })

  }
  doLogin = login => {
    const { username, password } = login
    console.log(username)
    if (username === "" || password === "") {
      alert("Masukan Data Lengkap !")
    } else {
      var cariName = this.state.users.map(function (e) {
        return e.username;
      }).indexOf(username);

      var cariPassword = this.state.users.map(function (e) {
        return e.password;
      }).indexOf(password);

      let dataLogin = this.state.users.filter(user => {
        return user.username === username
      })

      console.log("Data : ", cariName)
      console.log("Data password : ", cariPassword)
      console.log("username : ", username)
      // if (cariName < 0 && cariPassword < 0) {
      if (cariName >= 0) {
        if (cariPassword >= 0) {
          alert("Selamat Berhasil Login")
          this.props.submitLogin({userData: dataLogin[0]})
        } else {
          alert("password  not found !!")
        }
      } else {
        alert("username  not found !!")
      }
    }

  }

  tampilPage = () => {
    if (!this.props.checkLogin) {
      return (
        <Login
          doLogin={this.doLogin}
        />
      )
    } else
      return (
        <>
          <App />
          {/* <Keluar/>  */}
        </>
      )
  }
  render() {
    console.log(this.props.checkLogin)
    return (
      <>
        {this.tampilPage()}
      </>
    );
  }
}

const mapStateToProps = state => ({
  checkLogin: state.AReducer.isLogin,
  userLogin: state.AReducer.username
})

const mapDispatchToProps = dispatch => {
  return {
    submitLogin: (data) => dispatch({ type: "LOGIN_SUCCESS", payload: data }),
    keluar: () => dispatch({ type: "LOGOUT_SUCCESS" }),
    updateUser: payload => dispatch({ type: "SET_DATA", payload }),
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(Proses);