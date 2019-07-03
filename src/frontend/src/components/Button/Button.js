import React from 'react';

import classNames from 'classnames/bind';
import * as styles from './button.module.scss';

const cx = classNames.bind(styles);

const Button = ({ children, ...rest }) => {
  const { onClick, className } = rest;
  const style = ['button'];

  if (className) {
    if (Array.isArray(className)) {
      style.concat(className);
    } else {
      style.push(className);
    }
  }

  return (
    <div className={cx(style)} onClick={onClick}>
      {children}
    </div>
  );
};

export default Button;
