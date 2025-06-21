import "../../style/loginform.css";
import { useState } from "react";
import { loginUser } from "../../api/auth";
import { Link } from "react-router-dom";

function LoginForm() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await loginUser(formData);
      // Store token in localStorage
      localStorage.setItem("token", response.data.token);
      setMessage("login Successfull");
    } catch (err) {
      console.log(err);
      setMessage("Login Failed");
    }
  };

  return (
    <div className="form-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <label>Enter Email </label>
        <input
          type="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <label>Enter Password </label>
        <input
          type="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <button type="submit">submit</button>
        {message && <p>{message}</p>}
      </form>
      <p>
        Don't have account <Link to={"/register"}>Register</Link>{" "}
      </p>
    </div>
  );
}

export default LoginForm;
