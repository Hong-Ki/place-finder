import React from 'react';

import Button from '../Button/Button';
import classNames from 'classnames/bind';
import {
  FaAngleDoubleLeft,
  FaAngleDoubleRight,
  FaAngleLeft,
  FaAngleRight,
} from 'react-icons/fa';

import * as styles from './pageNavigator.module.scss';

const cx = classNames.bind(styles);

const PageNavigator = props => {
  const { page, pageTotal } = props;
  let start = page - 2;
  let end = page + 2;

  if (page < 3) {
    start = 1;
    end = 5;
  }

  if (pageTotal < 5) {
    end = pageTotal;
  }

  if (pageTotal - start < 3) {
    start = pageTotal - 4 > 0 ? pageTotal - 4 : 1;
    end = pageTotal;
  }

  const pages = [];

  const onClick = {
    number: e => {
      const { onChange } = props;
      const { innerHTML } = e.target;

      onChange(Number(innerHTML));
    },
    next: () => {
      const { onChange } = props;
      onChange(1);
    },
    before: () => {
      const { onChange, page } = props;
      onChange(page <= 1 ? 1 : page - 1);
    },
    first: () => {
      const { onChange } = props;
      onChange(1);
    },
    last: () => {
      const { onChange, pageTotal } = props;
      onChange(pageTotal);
    },
  };

  for (let i = start; i <= end; i++) {
    const className = ['button'];
    if (i === page) {
      className.push('active');
    }
    pages.push(
      <Button key={i} id={i} className={cx(className)} onClick={onClick.number}>
        {i}
      </Button>,
    );
  }

  return (
    <div className={cx('wrapper')}>
      <Button className={cx('button')} onClick={onClick.first}>
        <FaAngleDoubleLeft />
      </Button>
      <Button className={cx('button')} onClick={onClick.before}>
        <FaAngleLeft />
      </Button>
      {pages}
      <Button className={cx('button')} onClick={onClick.next}>
        <FaAngleRight />
      </Button>
      <Button className={cx('button')} onClick={onClick.last}>
        <FaAngleDoubleRight />
      </Button>
    </div>
  );
};

export default PageNavigator;
