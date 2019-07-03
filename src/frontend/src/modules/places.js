import { List, Map } from 'immutable';
import { createAction, handleActions } from 'redux-actions';
import axios from 'axios';

const SEARCH = 'places/SEARCH';
const SET_KEYWORD = 'places/SET_KEYWORD';
const SET_PLACES = 'places/SET_PLACES';
const SET_PAGE = 'places/SET_PAGE';

export const search = createAction(SEARCH);
export const setKeyword = createAction(SET_KEYWORD); // payload:String
export const setPage = createAction(SET_PAGE); // payload:Number
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
  places: List([
    /*Map({
      id: '9114752',
      phone: '02-2142-8924',
      x: '127.12233699146152',
      y: '37.49518183450978',
      shortcuts: 'https://map.kakao.com/link/map/9114752',
      category_name: '사회,공공기관 > 행정기관 > 고용노동부 > 고용센터',
      address_name: '서울 송파구 가락동 78',
      road_address_name: '서울 송파구 중대로 135',
      place_name: '서울동부고용복지플러스센터',
    }),
    Map({
      id: '7935311',
      phone: '1566-0112',
      x: '127.127161296581',
      y: '37.5019775317251',
      shortcuts: 'https://map.kakao.com/link/map/7935311',
      category_name: '사회,공공기관 > 행정기관 > 경찰서',
      address_name: '서울 송파구 가락동 9',
      road_address_name: '서울 송파구 중대로 221',
      place_name: '서울송파경찰서',
    }),
    Map({
      id: '17199379',
      phone: '02-3435-0202',
      x: '127.115606188341',
      y: '37.4950877000966',
      shortcuts: 'https://map.kakao.com/link/map/17199379',
      category_name: '사회,공공기관 > 공사,공단',
      address_name: '서울 송파구 가락동 600',
      road_address_name: '서울 송파구 양재대로 932',
      place_name: '서울시농수산식품공사 가락시장',
    }),*/
  ]),
});

export default handleActions(
  {
    [SEARCH]: (state, action) => {
      return state.set('searchKeyword', state.get('keyword')).set('page', 1);
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
    [SET_PAGE]: (state, action) => {
      return state.set('page', action.payload);
    },
  },
  initialState,
);
