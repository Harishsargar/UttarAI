// src/routes.js
import EmailReplyer from "./components/EmailReplyer";
import RegisterForm from "./components/auth/RegisterForm";
import LoginForm from "./components/auth/LoginForm";
import Home from "./components/Home";

export const protectedRoutes = [
  { path: "/emailReplyer", element: <EmailReplyer /> },
];

export const unprotectedRoutes = [
  { path: "/register", element: <RegisterForm /> },
  { path: "/login", element: <LoginForm /> },
  { path: "/home", element: <Home /> },
  { path: "/", element: <Home /> },
];
