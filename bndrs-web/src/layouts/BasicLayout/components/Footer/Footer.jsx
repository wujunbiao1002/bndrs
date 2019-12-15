import React, { PureComponent } from 'react';
import Layout from '@icedesign/layout';
import Logo from '../Logo';

import './Footer.scss';

export default class Footer extends PureComponent {
  render() {
    return (
      <Layout.Footer className="ice-design-layout-footer" type={null}>
        <div className="ice-design-layout-footer-body">
          <div style={{ filter: 'grayscale(50)' }}>
            {/*<Logo />*/}
          </div>
          <div className="copyright">
            © 2019 Website designed by{' '}
            <a
              href="https://github.com/alibaba/ice"
              target="_blank"
              className="copyright-link"
              rel="noopener noreferrer"
            >
              Junbiao Wu
            </a>
          </div>
        </div>
      </Layout.Footer>
    );
  }
}
