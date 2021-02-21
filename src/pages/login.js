import React, { Component } from 'react'

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users : [],
            username: "",
            password: "",
            
        }
    }

   
    setValue = e => {
        this.setState({
            [e.target.name]: e.target.value
        })
        console.log(e.target.value)
    }
    render() {
        const { username, password } = this.state
        return (
            <div className="container-form">
                <h1>Login</h1>
                <form >
                    <label>Username</label><br />
                    <input type="text" name="username" value={username} onChange={this.setValue} placeholder="Enter username..." /><br />
                    <label>Password</label><br />
                    <input type="password" name="password" value={password} onChange={this.setValue} placeholder="Enter password..." /><br />
                    <button type="button" onClick={() => this.props.doLogin({ username, password })}> Log in</button>
                </form>
            </div>
        );
    }
}

export default (Login);