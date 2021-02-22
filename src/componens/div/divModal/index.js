import React, { Component } from 'react'

class DivModal extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <div className={this.props.className} id={this.props.id}>
                {this.props.children}
            </div>
         );
    }
}
 
export default DivModal;