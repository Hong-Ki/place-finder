import { List } from 'immutable';
import { createAction, handleActions } from 'redux-actions';

export const SET_DATA = 'keyword/SET_DATA';

export const setData = createAction(SET_DATA);

const initialState = List();

export default handleActions(
  {
    [SET_DATA]: (state, action) => {
      return action.payload;
    },
  },
  initialState,
);
