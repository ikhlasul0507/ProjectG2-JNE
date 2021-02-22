import React, { Component } from 'react'

class Li extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <li className={this.props.className}>
                {this.props.children}
                </li>
         );
    }
}
 
export default Li;