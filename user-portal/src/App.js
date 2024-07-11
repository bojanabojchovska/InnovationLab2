import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  useEffect(() => {
    const username = localStorage.getItem('username');
    setIsLoggedIn(!!username);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('username');
    setIsLoggedIn(false);
    alert("Logout successful");
  };

  return (
      <Router>
        <div>
          {/* Navbar */}
          <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container-fluid">
              <Link className="navbar-brand" to="/">User Portal</Link>
              <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
              </button>
              <div className="collapse navbar-collapse" id="navbarNav">
                <ul className="navbar-nav">
                  {isLoggedIn ? (
                      <>
                        <li className="nav-item">
                          <a className="nav-link" href="#" onClick={handleLogout}>Logout</a>
                        </li>
                      </>
                  ) : (
                      <>
                        <li className="nav-item">
                          <Link className="nav-link" to="/login">Login</Link>
                        </li>
                        <li className="nav-item">
                          <Link className="nav-link" to="/register">Register</Link>
                        </li>
                      </>
                  )}
                </ul>
              </div>
            </div>
          </nav>

          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
          </Routes>
        </div>
      </Router>
  );
}

export default App;
