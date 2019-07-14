import { combineReducers } from 'redux';

import places from './places';
import maps from './maps';
import modal from './modal';
import keywords from './keywords';

export default combineReducers({
  places,
  maps,
  modal,
  keywords,
});
