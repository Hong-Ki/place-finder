import React from 'react';
import classNames from 'classnames/bind';
import * as styles from './styles/layout.module.scss';
import PlacesContainer from './containers/PlacesContainer';
import MapContainer from './containers/MapContainer';
import ModalContainer from './containers/ModalContainer';

const cx = classNames.bind(styles);

function App() {
  return (
    <div className={cx('wrapper')}>
      <PlacesContainer />
      <MapContainer />
      <ModalContainer />
    </div>
  );
}

export default App;
