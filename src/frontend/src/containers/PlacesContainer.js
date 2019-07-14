import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import * as placesActions from '../modules/places';
import * as mapsActions from '../modules/maps';
import * as modalActions from '../modules/modal';

import classNames from 'classnames/bind';
import * as styles from '../styles/layout.module.scss';

import PlaceList from '../components/PlaceList/PlaceList';
import SearchBox from '../components/SearchBox/SearchBox';
import PageNavigator from '../components/PageNavigator/PageNavigator';

const cx = classNames.bind(styles);

class PlacesContainer extends Component {
  onSearch = () => {
    const { PlacesActions, keyword } = this.props;
    PlacesActions.search({ keyword: keyword, page: 1 });
  };

  onHistory = () => {
    const { ModalActions } = this.props;
    ModalActions.setMode('HISTORY');
  };
  onPopular = () => {
    const { ModalActions } = this.props;
    ModalActions.setMode('POPULAR');
  };

  onChange = keyword => {
    const { PlacesActions } = this.props;
    PlacesActions.setKeyword(keyword);
  };

  onSelect = place => {
    const { MapsActions } = this.props;
    MapsActions.move(place);
  };

  onPage = page => {
    const { PlacesActions, searchKeyword } = this.props;
    PlacesActions.search({ keyword: searchKeyword, page: page });
  };

  render() {
    const { places, page, pageTotal, searchKeyword } = this.props;
    const { onSearch, onHistory, onPopular, onChange, onSelect, onPage } = this;
    return (
      <div className={cx('left')}>
        <SearchBox
          onSearch={onSearch}
          onChange={onChange}
          onHistory={onHistory}
          onPopular={onPopular}
          defaultValue={searchKeyword}
        />
        <PlaceList places={places} onSelect={onSelect} />
        {places.size > 0 && (
          <PageNavigator page={page} pageTotal={pageTotal} onChange={onPage} />
        )}
      </div>
    );
  }
}

export default connect(
  state => ({
    places: state.places.get('places'),
    page: state.places.get('page'),
    pageTotal: state.places.get('pageTotal'),
    keyword: state.places.get('keyword'),
    searchKeyword: state.places.get('searchKeyword'),
  }),
  dispatch => ({
    PlacesActions: bindActionCreators(placesActions, dispatch),
    MapsActions: bindActionCreators(mapsActions, dispatch),
    ModalActions: bindActionCreators(modalActions, dispatch),
  }),
)(PlacesContainer);
