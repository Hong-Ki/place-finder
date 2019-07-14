import { put, takeEvery } from 'redux-saga/effects';
import { SET_MODE } from '../modules/modal';
import { SET_DATA } from '../modules/keywords';

import { popularKeywordUri, historyKeywordUri } from '../common/Uris';
import { get, SUCCESS } from '../common/Request';
import { List, Map } from 'immutable';

function* getKeywrodsSaga(action) {
  const uri =
    action.payload === 'POPULAR' ? popularKeywordUri : historyKeywordUri;

  let list = List();

  try {
    const response = yield get(uri);
    const { data, result } = response.data;
    if (result === SUCCESS) {
      list = List(data.map(keyword => Map(keyword)));
    }
  } catch {
    window.alert('키워드를 가져오지 못했습니다.');
  }
  yield put({ type: SET_DATA, payload: list });
}

export function* keywordsSaga() {
  yield takeEvery(SET_MODE, getKeywrodsSaga);
}
