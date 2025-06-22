// src/App.jsx
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
// import Home from './components/Home';
import { protectedRoutes, unprotectedRoutes } from "./routes";

function PrivateRoute({ children }) {
  const token = localStorage.getItem("token");
  console.log('protected componet',token);
  return token ? children : <Navigate to="/login" />;
}

function App() {
  const token = localStorage.getItem("token");

  return (
    <Router>
      <Routes>
        {/* Unprotected Routes */}
        {unprotectedRoutes.map(({ path, element }) => (
          <Route key={path} path={path} element={element} />
        ))}

        {/* Protected routes from config */}
        {protectedRoutes.map(({ path, element }) => (
          <Route
            key={path}
            path={path}
            element={<PrivateRoute>{element}</PrivateRoute>}
          />
        ))}

        {/* Fallback */}
        <Route path="*" element={<Navigate to={"/home"} />} />
      </Routes>
    </Router>
  );
}

export default App;
