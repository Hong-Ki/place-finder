import React from 'react';
import * as styles from './place.module.scss';
import classNames from 'classnames/bind';

import { MdOpenInNew } from 'react-icons/md';
import Button from '../Button/Button';

const cx = classNames.bind(styles);

const Place = props => {
  const { place, onSelect, eventDisable } = props;
  const onShortcuts = e => {
    e.stopPropagation();
    e.preventDefault();
    window.open(place.get('shortcuts'));
  };
  const onClick = e => {
    onSelect(place);
  };

  return (
    <div
      className={cx(['placeBox', eventDisable && 'eventDisable'])}
      onClick={eventDisable ? null : onClick}
    >
      <div className={cx('title')}>
        <div className={cx('text')}>
          <div className={cx('category')}>{place.get('category_name')}</div>
          <div>{place.get('place_name')}</div>
        </div>
        {!eventDisable && (
          <Button className={cx('button')} onClick={onShortcuts}>
            <MdOpenInNew />
          </Button>
        )}
      </div>
      <div className={cx('detail')}>
        <div>
          <div>
            <span>지번 주소: </span>
            {place.get('address_name')}
          </div>
          <div>
            <span>도로명 주소: </span>
            {place.get('road_address_name')}
          </div>
          <div>
            <span>전화번호: </span>
            {place.get('phone')}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Place;
