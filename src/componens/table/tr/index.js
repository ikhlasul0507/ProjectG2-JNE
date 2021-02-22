import React, { Component } from 'react'

class Tr extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <tr>
                {this.props.children}
                </tr>
         );
    }
}
 
export default Tr;