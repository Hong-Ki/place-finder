import { List, Map } from 'immutable';
import { createAction, handleActions } from 'redux-actions';

export const SEARCH = 'places/SEARCH';
export const SET_PLACES = 'places/SET_PLACES';
const SET_KEYWORD = 'places/SET_KEYWORD';

export const search = createAction(SEARCH);
export const setKeyword = createAction(SET_KEYWORD); // payload:String
export const setPlaces = createAction(SET_PLACES);
/**
 * payload:{
 *  places:List(),
 *  page:Number,
 *  pageTotal:Number
 */

const initialState = Map({
  searchKeyword: '',
  keyword: '',
  page: 0,
  pageTotal: 0,
  places: List(),
});

export default handleActions(
  {
    [SEARCH]: (state, action) => {
      return state
        .set('keyword', action.payload.keyword)
        .set('searchKeyword', action.payload.keyword)
        .set('page', action.payload.page);
    },
    [SET_KEYWORD]: (state, action) => {
      return state.set('keyword', action.payload);
    },
    [SET_PLACES]: (state, action) => {
      return state
        .set('places', action.payload.places)
        .set('page', action.payload.page)
        .set('pageTotal', action.payload.pageTotal);
    },
  },
  initialState,
);
