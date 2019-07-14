import { Map } from 'immutable';
import { createAction, handleActions } from 'redux-actions';

const SET_VISIBLE = 'modal/SET_VISIBLE';
export const SET_MODE = 'modal/SET_MODE';

export const setVisible = createAction(SET_VISIBLE);
export const setMode = createAction(SET_MODE);

const initialState = Map({
  visible: true,
  mode: '',
});

export default handleActions(
  {
    [SET_VISIBLE]: (state, action) => {
      return state.set('visible', action.payload);
    },
    [SET_MODE]: (state, action) => {
      return state.set('mode', action.payload).set('visible', true);
    },
  },
  initialState,
);
