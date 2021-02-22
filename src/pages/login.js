import React, { Component } from 'react'
import Button from "../componens/button"
import Label from "../componens/label"
import Input from "../componens/input"
import H1 from "../componens/h1"
import Form from "../componens/form"
import CFrom from "../componens/div/cForm"

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
            <CFrom className="container-form">
                <H1>Login SIMANKET</H1>
                <Form >
                    <Label>Username</Label><br />
                    <Input type="text" name="username" value={username} onChange={this.setValue} placeholder="Enter username..." /><br />
                    <Label>Password</Label><br />
                    <Input type="password" name="password" value={password} onChange={this.setValue} placeholder="Enter password..." /><br />
                    <Button type="button" onClick={() => this.props.doLogin({ username, password })}>Log in</Button>
                </Form>
            </CFrom>
        );
    }
}

export default (Login);