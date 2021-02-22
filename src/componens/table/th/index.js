import React, { Component } from 'react'

class Th extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <th scope={this.props.scope}>
                {this.props.children}
                </th>
         );
    }
}
 
export default Th;