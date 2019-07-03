import { combineReducers } from 'redux';

import places from './places';
import maps from './maps';

export default combineReducers({
  places,
  maps,
});
