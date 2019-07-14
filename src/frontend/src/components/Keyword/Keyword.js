import React from 'react';
import classNames from 'classnames/bind';

import * as styles from './keyword.module.scss';

const cx = classNames.bind(styles);

const Keyword = ({ keyword, mode, onSearch }) => {
  const onClick = e => {
    onSearch(keyword.get('keyword'));
  };
  return (
    <div className={cx('wrapper')} onClick={onClick}>
      <div className={cx('keyword')}>{keyword.get('keyword')}</div>
      <div className={cx('etc')}>
        <span>{keyword.get(mode === 'POPULAR' ? 'count' : 'date')}</span>
      </div>
    </div>
  );
};

export default Keyword;
