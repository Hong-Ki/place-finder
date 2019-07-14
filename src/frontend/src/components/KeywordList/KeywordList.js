import React from 'react';
import Keyword from '../Keyword/Keyword';

import * as styles from './keywordList.module.scss';

import classNames from 'classnames/bind';
import Button from '../Button/Button';

import { MdExitToApp } from 'react-icons/md';

const cx = classNames.bind(styles);

const KeywordList = ({ keywords, mode, onExit, onSearch }) => {
  const elements = keywords.map(keyword => (
    <Keyword
      key={keyword.get('id')}
      keyword={keyword}
      mode={mode}
      onSearch={onSearch}
    />
  ));
  return (
    <div className={cx('left')}>
      <div className={cx('title')}>
        <span>{mode === 'POPULAR' ? '인기 키워드' : '검색 목록'}</span>
        <Button className={cx('button')} onClick={onExit}>
          <MdExitToApp />
        </Button>
      </div>
      <div className={cx('list')}>{elements}</div>
    </div>
  );
};

export default KeywordList;
