import { put, takeEvery } from 'redux-saga/effects';
import { SEARCH, SET_PLACES } from '../modules/places';

import { searchUri } from '../common/Uris';
import { get, SUCCESS } from '../common/Request';
import { List, Map } from 'immutable';

function* getPlacesSaga(action) {
  const uri = searchUri;

  const payload = {
    places: List(),
    page: 0,
    pageTotal: 0,
  };

  try {
    const { keyword, page } = action.payload;
    const response = yield get(uri, { keyword: keyword, page: page });
    const { data, result } = response.data;
    if (result === SUCCESS) {
      payload.places = List(data.places.map(place => Map(place)));
      payload.page = page;
      payload.pageTotal = data.pageTotal;
    }
  } catch {
    window.alert('검색을 요청하지 못했습니다.');
  }
  yield put({ type: SET_PLACES, payload: payload });
}

export function* placesSaga() {
  yield takeEvery(SEARCH, getPlacesSaga);
}
