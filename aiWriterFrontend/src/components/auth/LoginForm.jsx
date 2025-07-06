import "../../style/loginform.css";
import { useState } from "react";
import { loginUser } from "../../api/auth";
import { useNavigate, Link } from "react-router-dom";
  import img2 from "../../assets/img5-min.png";


function LoginForm() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const navigate = useNavigate();

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
      const token = response.data.token;
      // Store token in localStorage
      localStorage.setItem("token", token);
      // Example in JavaScript or TypeScript
      document.cookie = `jwt=${token}; path=/; max-age=3600`; // 1 day
      setMessage("login Successfull");
      setTimeout(() => {
        navigate("/home");
      }, 1000);
    } catch (err) {
      console.log(err);
      setMessage("Login Failed");
    }
  };

  return (
    <div className="registerform-container">
      <div className="left-side-container">
        <h4>Welocome to</h4>
        <h1>उत्तर-AI</h1>
        <p><span>Uttar-AI</span> is Context-aware, intelligent Reply-Generation platform.</p>
        <img src={img2} height={400} width={400} />
      </div>

      <div className="form-container">
        <h2 className="login-form-heading">Login Form</h2>
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
          {message && <p className="login-message">{message}</p>}
        </form>
        <p className="register-form-link-msg">
          Don't have account <Link to={"/register"}>Register</Link>{" "}
        </p>
      </div>
    </div>
  );
}

export default LoginForm;
