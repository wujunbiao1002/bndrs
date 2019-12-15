import React, { Component } from 'react';
import { Input, Button, Dialog, Message, Select } from '@alifd/next';
import FoundationSymbol from '@icedesign/foundation-symbol';
import axios from 'axios';
import qs from 'qs';
import { FormBinderWrapper, FormBinder, FormError } from '@icedesign/form-binder';
import URL from '../../../../config/serverConfig';

export default class ShareDialog extends Component {
  constructor(props) {
    super(props);
    this.state = {
      visible: false,
      align: 'cc cc',
      style: {
        width: '50%',
      },
      value: {
        title: '',
        url: '',
        type: '其他',
        password: '',
      },
    };
  }
  validateFields = () => {
    const { validateFields } = this.refs.form;
    validateFields((errors, values) => {
      if (!errors) {
        const data = {
          title: this.state.value.title,
          url: this.state.value.url,
          type: this.state.value.type,
          password: this.state.value.password,
        };
        const url = `${URL.HOST}resource/add.do`;
        axios.post(url, qs.stringify(data), {
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
          },
        })
          .then((response) => {
            Dialog.alert({
              title: '分享提示信息',
              content: response.data.message,
              onOk: () => {
                this.onClose();
              },
            });
          });
      }
    });
  };
  render() {
    const types = [
      { label:'视频', value:'视频' },
      { label:'音乐', value:'音乐' },
      { label:'图片', value:'图片' },
      { label:'文档', value:'文档' },
      { label:'程序', value:'程序' },
      { label:'其他', value:'其他' },
      { label:'种子', value:'种子' },
    ];
    return (
      <div>
        <a style={styles.link} onClick={this.onOpen}>
          <FoundationSymbol type="forward" size="small" />
          分享我的网盘资源
        </a>
        <Dialog
          title="分享我的网盘资源"
          visible={this.state.visible}
          onOk={this.validateFields.bind(this)}
          onCancel={this.onClose.bind(this, 'cancelClick')}
          onClose={this.onClose}
          style={this.state.style}
          align={this.state.align}
        >
          <div style={styles.container}>
            <FormBinderWrapper
              value={this.state.value}
              ref="form"
            >
              <div style={styles.content}>
                <div style={styles.formItem}>
                  <div style={styles.formLabel}>分享标题：</div>
                  <FormBinder name="title" required type="string" message="请输入分享标题" >
                    <Input placeholder="请输入分享标题" style={{ width: '75%' }} />
                  </FormBinder>
                  <FormError style={styles.formItemError} name="title" />
                </div>

                <div style={styles.formItem}>
                  <div style={styles.formLabel}>分享链接：</div>
                  <FormBinder name="url" required type="url" message="请输入正确的百度网盘链接" >
                    <Input placeholder="请输入百度网盘链接" style={{ width: '75%' }} />
                  </FormBinder>
                  <FormError style={styles.formItemError} name="url" />
                </div>

                <div style={styles.formItem}>
                  <div style={styles.formLabel}>分享密码：</div>
                  <FormBinder name="password" type="string" message="请输入连接密码" >
                    <Input placeholder="请输入连接密码" style={{ width: '75%' }} />
                  </FormBinder>
                  <FormError style={styles.formItemError} name="password" />
                </div>

                <div style={styles.formItem}>
                  <div style={styles.formLabel}>类别：</div>
                  <FormBinder name="type" required message="请选择类型" >
                    <Select dataSource={types}
                      onChange={(value) => {
                        this.setState({ type: value });
                    }}
                      type="string"
                      name="type"
                    />
                  </FormBinder>
                  <FormError style={styles.formItemError} name="type" />
                </div>
              </div>
            </FormBinderWrapper>
          </div>
        </Dialog>
      </div>
    );
  }
  onOpen = () => {
    const data = {
      title: '',
      url: '',
      type: '其他',
      password: '',
    };
    this.setState({
      visible: true,
      value: data,
    });
  };
  onClose = reason => {
    this.setState({
      visible: false,
    });
  };
}

const styles = {
  container: {
    margin: '0px',
  },
  link: {
    margin: '0 5px',
    color: 'rgba(49, 128, 253, 0.65)',
    cursor: 'pointer',
    textDecoration: 'none',
  },
  separator: {
    margin: '0 8px',
    display: 'inline-block',
    height: '12px',
    width: '1px',
    verticalAlign: 'middle',
    background: '#e8e8e8',
  },
  pagination: {
    marginTop: '5px',
    textAlign: 'right',
  },
  title: {
    marginBottom: '10px',
    fontSize: '16px',
    fontWeight: '500',
    color: 'rgba(0, 0, 0,.85)',
  },
  summary: {
    margin: '0 0 20px',
  },
  formItem: {
    marginBottom: '20px',
  },
  formLabel: {
    marginBottom: '10px',
  },
  formError: {
    marginTop: '10px',
  },
  editIcon: {
    position: 'absolute',
    right: '0',
    bottom: '0',
    cursor: 'pointer',
  },
  button: {
    marginLeft: '20px',
  },
};
