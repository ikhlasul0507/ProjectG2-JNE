import React, { Component } from 'react'

class Nav extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return ( 
            <nav>
                {this.props.children}
            </nav>
         );
    }
}
 
export default Nav;