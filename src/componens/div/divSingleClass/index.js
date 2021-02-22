import React, { Component } from 'react'

class DivSingle extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <div className={this.props.className}>
                {this.props.children}
            </div>
         );
    }
}
 
export default DivSingle;