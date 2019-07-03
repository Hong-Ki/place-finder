import { combineReducers } from 'redux';

import places from './places';
import maps from './maps';
import modal from './modal';

export default combineReducers({
  places,
  maps,
  modal,
});
