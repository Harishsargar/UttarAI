// src/App.jsx
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Navigate,
} from "react-router-dom";
import { useEffect } from "react";
// import Home from './components/Home';
import { protectedRoutes, unprotectedRoutes } from "./routes";
import { pingBackend } from "./api/pingBackend";

function PrivateRoute({ children }) {
  const token = localStorage.getItem("token");
  console.log('protected componet',token);
  return token ? children : <Navigate to="/login" />;
}

function App() {
  const token = localStorage.getItem("token");


  // this is use to ping thhe backend at the site reload so that backend will spin up..
  useEffect(() => {
    // Call on initial load
    pingBackend();
    // Set interval to keep backend warm
    const interval = setInterval(() => {
      pingBackend();
    }, 600000 ); // 10 minutes

    // Clean up interval on unmount
    return () => clearInterval(interval);
  }, []);

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
