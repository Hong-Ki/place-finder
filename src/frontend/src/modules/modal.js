import { Map } from 'immutable';
import { createAction, handleActions } from 'redux-actions';

const SET_VISIBLE = 'modal/SET_VISIBLE';

export const setVisible = createAction(SET_VISIBLE);

const initialState = Map({
  visible: true,
});

export default handleActions(
  {
    [SET_VISIBLE]: (state, action) => {
      return state.set('visible', action.payload);
    },
  },
  initialState,
);
