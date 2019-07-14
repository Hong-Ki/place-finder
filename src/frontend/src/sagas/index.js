import { all } from 'redux-saga/effects';

import { keywordsSaga } from './keywords';
import { placesSaga } from './places';

export function* rootSaga() {
  yield all([keywordsSaga(), placesSaga()]);
}
