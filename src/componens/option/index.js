import React, { Component } from 'react'

class Option extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <option value={this.props.value} defaultValue={this.props.defaultValue}>{this.props.children}</option>
         );
    }
}
 
export default Option;