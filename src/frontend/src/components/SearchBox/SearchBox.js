import React from 'react';
import * as styles from './searchBox.module.scss';
import classNames from 'classnames/bind';

import Button from '../Button/Button';
import { MdHistory, MdSearch, MdShowChart } from 'react-icons/md';

const cx = classNames.bind(styles);

const SearchBox = ({
  defaultValue,
  onChange,
  onSearch,
  onHistory,
  onPopular,
}) => {
  const onChangeKeyword = e => {
    const { value } = e.target;

    onChange(value);
  };

  return (
    <div className={cx('wrapper')}>
      <Button className={cx('button')} onClick={onHistory}>
        <MdHistory />
      </Button>
      <Button className={cx('button')} onClick={onPopular}>
        <MdShowChart />
      </Button>
      <input
        placeholder={'키워드를 입력해주세요..'}
        onChange={onChangeKeyword}
        defaultValue={defaultValue}
      />
      <Button className={cx('button')} onClick={onSearch}>
        <MdSearch />
      </Button>
    </div>
  );
};

export default SearchBox;
