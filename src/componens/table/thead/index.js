import React, { Component } from 'react'

class Thead extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <thead>
                {this.props.children}
            </thead>
         );
    }
}
 
export default Thead;