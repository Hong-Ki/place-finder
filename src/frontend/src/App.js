import React from 'react';
import classNames from 'classnames/bind';
import * as styles from './styles/layout.module.scss';
import PlacesContainer from './containers/PlacesContainer';
import MapContainer from './containers/MapContainer';

const cx = classNames.bind(styles);

function App() {
  return (
    <div className={cx('wrapper')}>
      <PlacesContainer />
      <MapContainer />
    </div>
  );
}

export default App;
