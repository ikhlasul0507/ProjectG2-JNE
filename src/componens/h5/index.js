import React, { Component } from 'react'

class H5 extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <h5 className={this.props.className} id={this.props.id}>{this.props.children}</h5>
         );
    }
}
 
export default H5;