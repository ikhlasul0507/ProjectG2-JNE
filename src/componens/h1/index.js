import React, { Component } from 'react'

class H1 extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <h1>{this.props.children}</h1>
         );
    }
}
 
export default H1;