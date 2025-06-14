import { useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';

function OAuthCallback() {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const token = params.get('accessToken');

    if (token) {
      localStorage.setItem('accessToken', token);
      navigate('/');
    } else {
      alert('로그인에 실패했습니다.');
      navigate('/login');
    }
  }, [location.search, navigate]);

  return <div className="bg-primary-950"></div>;
}

export default OAuthCallback;
