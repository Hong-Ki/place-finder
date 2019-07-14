import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import * as modalActions from '../modules/modal';
import * as keywordsActions from '../modules/keywords';
import * as placesActions from '../modules/places';
import LoginModal from '../components/LoginModal/LoginModal';
import KeywordList from '../components/KeywordList/KeywordList';

import classNames from 'classnames/bind';
import * as styles from '../styles/layout.module.scss';

import { get } from '../common/Request';
import { tokenCheckUri } from '../common/Uris';

const cx = classNames.bind(styles);

class ModalContainer extends Component {
  componentDidMount() {
    get(tokenCheckUri).then(response => {
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

  onExit = () => {
    const { ModalActions } = this.props;
    ModalActions.setVisible(false);
  };

  onSearch = keyword => {
    const { PlacesActions } = this.props;
    PlacesActions.search({ keyword: keyword, page: 1 });
    this.onExit();
  };

  render() {
    const { visible, mode, keywords } = this.props;
    const { onLogin, onExit, onSearch } = this;
    return (
      <div className={cx(['modal', visible ? '' : 'hide'])}>
        {(mode === '' || mode === 'LOGIN') && <LoginModal onLogin={onLogin} />}
        {(mode === 'HISTORY' || mode === 'POPULAR') && (
          <KeywordList
            keywords={keywords}
            mode={mode}
            onExit={onExit}
            onSearch={onSearch}
          />
        )}
      </div>
    );
  }
}

export default connect(
  state => ({
    visible: state.modal.get('visible'),
    mode: state.modal.get('mode'),
    keywords: state.keywords,
  }),
  dispatch => ({
    ModalActions: bindActionCreators(modalActions, dispatch),
    KeywordsActions: bindActionCreators(keywordsActions, dispatch),
    PlacesActions: bindActionCreators(placesActions, dispatch),
  }),
)(ModalContainer);
