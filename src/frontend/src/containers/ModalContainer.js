import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import * as modalActions from '../modules/modal';
import LoginModal from '../components/LoginModal/LoginModal';

import classNames from 'classnames/bind';
import * as styles from '../styles/layout.module.scss';

import axios from 'axios';
import { tokenCheckUri } from '../common/Uris';

const cx = classNames.bind(styles);

class ModalContainer extends Component {
  componentDidMount() {
    axios
      .get(tokenCheckUri, {
        headers: { jwt: sessionStorage.getItem('jwt') },
      })
      .then(response => {
        const { data } = response;
        const { ModalActions } = this.props;
        if (data.result === 'SUCCESS') {
          ModalActions.setVisible(false);
        }
      });
  }

  onLogin = result => {
    const { ModalActions } = this.props;
    ModalActions.setVisible(false);
  };

  render() {
    const { visible } = this.props;
    const { onLogin } = this;
    return (
      <div className={cx(['modal', visible ? '' : 'hide'])}>
        <LoginModal onLogin={onLogin} />
      </div>
    );
  }
}

export default connect(
  state => ({
    visible: state.modal.get('visible'),
  }),
  dispatch => ({
    ModalActions: bindActionCreators(modalActions, dispatch),
  }),
)(ModalContainer);
