import axios from 'axios';

const defaultHeaders = () => {
  return { jwt: sessionStorage.getItem('jwt') };
};
const error = error => {
  console.error(error);
};

export const SUCCESS = 'SUCCESS';
export const FAIL = 'FAIL';

export const get = (uri, params = {}, headers = {}, errCallback = error) => {
  const options = {
    params: params,
    headers: Object.assign(Object.assign({}, headers), defaultHeaders()),
  };
  return axios.get(uri, options).catch();
};
export const post = (uri, params = {}, headers = {}) => {
  const options = {
    params: params,
    headers: Object.assign(Object.assign({}, headers), defaultHeaders()),
  };
  return axios.post(uri, options).catch();
};
export const put = (uri, params = {}, headers = {}) => {
  const options = {
    params: params,
    headers: Object.assign(Object.assign({}, headers), defaultHeaders()),
  };
  return axios.put(uri, options).catch();
};
export const remove = (uri, params = {}, headers = {}) => {
  const options = {
    params: params,
    headers: Object.assign(Object.assign({}, headers), defaultHeaders),
  };
  return axios.delete(uri, options).catch();
};
