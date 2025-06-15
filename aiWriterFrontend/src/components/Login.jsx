import { useState } from "react";
import RegisterForm from "./auth/RegisterForm";
import LoginForm from "./auth/LoginForm";

function Login() {
  const [showRegister, setShowRegister] = useState(false);

  return (
    <div>
      {showRegister ? <RegisterForm /> : <LoginForm />}

      <button onClick={() => setShowRegister((prev) => !prev)}>
        {showRegister ? "Already have an account? Login" : "New user? Register"}
      </button>
    </div>
  );
}

export default Login;
