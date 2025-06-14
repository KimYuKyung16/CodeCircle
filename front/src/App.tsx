import Home from '@pages/Home';
import Login from '@pages/Login';
import OAuthCallback from '@pages/Login/OAuthCallback';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/oauth2/google/callback" element={<OAuthCallback />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
