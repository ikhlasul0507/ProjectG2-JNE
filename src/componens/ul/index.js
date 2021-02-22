import React, { Component } from 'react'
class Ul extends Component {
    constructor(props) {
        super(props);
        this.state = {  }
    }
    render() { 
        return (
            <ul className={this.props.className}>
                {this.props.children}
            </ul>

          );
    }
}
 
export default Ul;