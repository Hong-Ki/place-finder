import React from 'react';
import * as styles from './searchBox.module.scss';
import classNames from 'classnames/bind';

import Button from '../Button/Button';
import { MdHistory, MdSearch, MdShowChart } from 'react-icons/md';

const cx = classNames.bind(styles);

const SearchBox = props => {
  const onChange = e => {
    const { onChange } = props;
    const { value } = e.target;

    onChange(value);
  };

  return (
    <div className={cx('wrapper')}>
      <Button className={cx('button')} onClick={props.onHistory}>
        <MdHistory />
      </Button>
      <Button className={cx('button')} onClick={props.onPopular}>
        <MdShowChart />
      </Button>
      <input placeholder={'키워드를 입력해주세요..'} onChange={onChange} />
      <Button className={cx('button')} onClick={props.onSearch}>
        <MdSearch />
      </Button>
    </div>
  );
};

export default SearchBox;
