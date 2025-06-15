// src/App.jsx
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from 'react-router-dom';
import Login from './components/Login';
import { protectedRoutes } from './routes';

function PrivateRoute({ children }) {
  const token = localStorage.getItem('token');
  console.log(token)
  return token ? children : <Navigate to="/login" />;
}

function App() {
  const token = localStorage.getItem('token');

  return (
    <Router>
      <Routes>
        {/* Public route */}
        <Route
          path="/login"
          element={<Login onLogin={() => (window.location.href = '/dashboard')} />}
        />


        {/* Protected routes from config */}
        {protectedRoutes.map(({ path, element }) => (
          <Route
            key={path}
            path={path}
            element={<PrivateRoute>{element}</PrivateRoute>}
          />
        ))}

        {/* Fallback */}
        <Route
          path="*"
          element={<Navigate to={token ? '/dashboard' : '/login'} />}
        />
      </Routes>
    </Router>
  );
}

export default App;
