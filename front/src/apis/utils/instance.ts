import axios from 'axios';

const BASE_URL = import.meta.env.VITE_BackEndUrl + '/apis';

const defaultApi = axios.create({
  baseURL: BASE_URL,
  headers: { 'Content-Type': 'application/json' },
  withCredentials: true,
});

const authApi = axios.create({
  baseURL: BASE_URL,
  withCredentials: true,
});

authApi.interceptors.request.use(
  (config) => {
    const accessToken = localStorage.getItem('accessToken');
    if (accessToken) {
      config.headers['Authorization'] = `Bearer ${accessToken}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

authApi.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response.status === 401) {
      alert('로그인 실패');
    }
    return Promise.reject(error);
  }
);

export const defaultInstance = defaultApi;
export const authInstance = authApi;
