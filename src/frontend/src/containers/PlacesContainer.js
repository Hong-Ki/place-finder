import React, { Component } from 'react';
import { Map, List } from 'immutable';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import * as placesActions from '../modules/places';
import * as mapsActions from '../modules/maps';

import axios from 'axios';

import classNames from 'classnames/bind';
import * as styles from '../styles/layout.module.scss';

import PlaceList from '../components/PlaceList/PlaceList';
import SearchBox from '../components/SearchBox/SearchBox';
import PageNavigator from '../components/PageNavigator/PageNavigator';

const cx = classNames.bind(styles);

class PlacesContainer extends Component {
  componentDidUpdate(prevProps, prevState, snapshot) {
    if (
      prevProps.page === this.props.page &&
      prevProps.keyword === this.props.keyword
    ) {
      return false;
    }

    const { keyword, page, PlacesActions } = this.props;
    if (keyword && keyword !== '') {
      axios
        .get('http://localhost:8080/search', {
          params: { keyword: keyword, userId: 'min3259', page: page },
        })
        .then(response => {
          const { data } = response.data;
          const payload = {
            places: List(data.places.map(place => Map(place))),
            page: page,
            pageTotal: data.pageTotal,
          };
          PlacesActions.setPlaces(payload);
        })
        .catch(error => {
          console.error(error);
        });
    } else {
      const payload = {
        places: List([]),
        page: 0,
        pageTotal: 0,
      };
      PlacesActions.setPlaces(payload);
    }
  }

  onSearch = () => {
    const { PlacesActions } = this.props;
    PlacesActions.search();
  };

  onHistory = () => {};
  onPopular = () => {};

  onChange = keyword => {
    const { PlacesActions } = this.props;
    PlacesActions.setKeyword(keyword);
  };

  onSelect = place => {
    const { MapsActions } = this.props;
    MapsActions.move(place);
  };

  onPage = page => {
    const { PlacesActions } = this.props;
    PlacesActions.setPage(page);
  };

  render() {
    const { places, page, pageTotal } = this.props;
    const { onSearch, onHistory, onPopular, onChange, onSelect, onPage } = this;
    return (
      <div className={cx('left')}>
        <SearchBox
          onSearch={onSearch}
          onChange={onChange}
          onHistory={onHistory}
          onPopular={onPopular}
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
    keyword: state.places.get('searchKeyword'),
  }),
  dispatch => ({
    PlacesActions: bindActionCreators(placesActions, dispatch),
    MapsActions: bindActionCreators(mapsActions, dispatch),
  }),
)(PlacesContainer);
