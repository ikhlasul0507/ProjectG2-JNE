import React, { Component } from 'react';
import { connect } from "react-redux";

class Keluar extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }

    logout = () => {
        if(window.confirm("Uy Yakin Kau Nak Keluar ?")){
        this.props.logoutAction()
        this.props.history.push("/login");
        }
    }
    render() { 
        this.logout();
        return (  
            <></>
        );
    }
}

const mapDispatchToProps = dispatch => {
    return {
        logoutAction : () => dispatch({type:"LOGOUT_SUCCESS"})
    }
}
 
export default connect(null, mapDispatchToProps)(Keluar);