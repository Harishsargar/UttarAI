// src/routes.js
import EmailReplyer from "./components/EmailReplyer";
import RegisterForm from "./components/auth/RegisterForm";
import LoginForm from "./components/auth/LoginForm";
import Home from "./components/Home";
import Pricing from "./components/Pricing";

export const protectedRoutes = [
  { path: "/emailReplyer", element: <EmailReplyer /> },

];

export const unprotectedRoutes = [
  { path: "/register", element: <RegisterForm /> },
  { path: "/pricing", element: <Pricing /> },
  { path: "/login", element: <LoginForm /> },
  { path: "/home", element: <Home /> },
  { path: "/", element: <Home /> },
];
