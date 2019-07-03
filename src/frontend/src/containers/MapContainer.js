import React, { Component } from 'react';
import { connect } from 'react-redux';
import Place from '../components/Place/Place';
import { Map } from 'immutable';

import classNames from 'classnames/bind';
import * as styles from '../styles/layout.module.scss';

const cx = classNames.bind(styles);

class MapContainer extends Component {
  constructor(props) {
    super(props);
    this.mapRef = React.createRef();
    this.divRef = React.createRef();
    this.map = null;
    this.marker = null;
    this.overlay = null;
  }

  componentDidMount() {
    const options = {
      center: new window.kakao.maps.LatLng(33.450701, 126.570667),
      level: 3,
    };
    this.map = new window.kakao.maps.Map(this.mapRef.current, options);
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    if (prevProps.lng !== this.props.lng && prevProps.lat !== this.props.lat) {
      const { lat, lng, place } = this.props;

      const latLng = new window.kakao.maps.LatLng(lat, lng);
      if (this.marker) {
        this.marker.setMap(null);
      }
      if (this.overlay) {
        this.overlay.setMap(null);
      }
      this.marker = new window.kakao.maps.Marker({
        map: this.map,
        position: new window.kakao.maps.LatLng(lat, lng),
      });

      this.map.setLevel(3);
      this.map.panTo(latLng);
      const content = this.divRef.current;
      const overlyPosition = this.marker.getPosition();
      // 마커보이기위한 매직넘버 수정필요
      overlyPosition.Ga = overlyPosition.Ga + 0.0012;
      this.overlay = new window.kakao.maps.CustomOverlay({
        content: content,
        map: this.map,
        position: overlyPosition,
      });
    }
  }

  render() {
    const { place } = this.props;
    const className = ['overlay'];
    if (!place) {
      className.push('hide');
    }

    return (
      <div className={cx('right')}>
        <div ref={this.mapRef} className={cx('map')} />
        <div ref={this.divRef} className={cx(className)}>
          <Place place={place ? place : Map()} eventDisable={true} />
        </div>
      </div>
    );
  }
}

export default connect(
  state => ({
    lat: state.maps.get('lat'),
    lng: state.maps.get('lng'),
    place: state.maps.get('place'),
  }),
  dispatch => ({}),
)(MapContainer);
