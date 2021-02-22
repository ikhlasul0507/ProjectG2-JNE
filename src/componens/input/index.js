import React, { Component } from 'react'

class Input extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <input type={this.props.type} className={this.props.className} name={this.props.name} value={this.props.value} onChange={this.props.onChange} placeholder={this.props.placeholder} />
         );
    }
}
 
export default Input;