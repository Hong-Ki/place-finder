import React, { Component } from 'react';

import Button from '../Button/Button';
import classNames from 'classnames/bind';
import * as styles from './modal.module.scss';

import { IoMdLogIn } from 'react-icons/io';

import { loginUri } from '../../common/Uris';
import { post } from 'axios';

const cx = classNames.bind(styles);

class LoginModal extends Component {
  constructor(props) {
    super(props);
    this.formRef = React.createRef();
  }
  onLogin = e => {
    const form = this.formRef.current;
    const formData = new FormData(form);

    //axios.post 사용.. Request.post사용시 에러
    post(loginUri, formData).then(response => {
      const { onLogin } = this.props;
      const { result } = response.data;
      const jwt = response.data.data;
      if (result === 'SUCCESS') {
        sessionStorage.setItem('jwt', jwt);
        onLogin(true);
      } else {
        alert('로그인에 실패하였습니다.');
      }
    });
  };

  render() {
    const { onLogin } = this;
    return (
      <div className={cx('wrapper')}>
        <div className={cx('title')}>LOGIN</div>
        <form ref={this.formRef}>
          <div className={cx('contents')}>
            <input placeholder={'Id'} name={'id'} />
            <input
              placeholder={'Password'}
              name={'password'}
              type={'password'}
            />
          </div>
          <div className={cx('buttons')}>
            <Button className={cx('login')} onClick={onLogin}>
              <IoMdLogIn />
            </Button>
          </div>
        </form>
      </div>
    );
  }
}

export default LoginModal;
