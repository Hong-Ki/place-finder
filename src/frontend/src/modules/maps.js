import { Map } from 'immutable';
import { createAction, handleActions } from 'redux-actions';

const MOVE = 'map/MOVE';

export const move = createAction(MOVE);

const initialState = Map({
  place: null,
  lng: 0,
  lat: 0,
});

export default handleActions(
  {
    [MOVE]: (state, action) => {
      return state
        .set('place', action.payload)
        .set('lat', action.payload.get('y'))
        .set('lng', action.payload.get('x'));
    },
  },
  initialState,
);
