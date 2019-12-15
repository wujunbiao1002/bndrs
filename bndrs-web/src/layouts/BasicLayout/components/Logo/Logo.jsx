import React, { PureComponent } from 'react';
import { Link } from 'react-router-dom';
import './Logo.scss';
import { Search } from '@alifd/next';
const logo = require('../../../../../public/logo.png');

export default class Logo extends PureComponent {
  render() {
    return (
      <div className="logo">
        <Link to="/" className="logo-text" >
          {/*百度网盘资源搜索*/}
          <img src={logo} alt="" />
        </Link>
      </div>
    );
  }
}
