import React from 'react';
import * as styles from './placeList.module.scss';
import classNames from 'classnames/bind';

import Place from '../Place/Place';

const cx = classNames.bind(styles);

const PlaceList = props => {
  const { places, onSelect } = props;

  const placeBoxs = places.map(place => (
    <Place key={place.get('id')} place={place} onSelect={onSelect} />
  ));

  return <div className={cx('wrapper')}>{placeBoxs}</div>;
};

export default PlaceList;
