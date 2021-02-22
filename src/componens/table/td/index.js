import React, { Component } from 'react'

class Td extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <td>
                {this.props.children}
                </td>
         );
    }
}
 
export default Td;