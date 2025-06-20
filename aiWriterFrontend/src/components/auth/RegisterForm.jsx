import "../../style/registerform.css";
import { registerUser } from "../../api/auth";
import { useState } from "react";

function RegisterForm() {
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    phoneNumber: "",
    gender: "",
    password: "",
    confirmPassword: "",
  });

  const [message, setMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit= async (e)=>{
    e.preventDefault();
    if(formData.password != formData.confirmPassword){
        setMessage("Password did not match !!");
        return;
    }
    try{
        await registerUser(formData);
        setMessage('Registraion Successfull');
    }catch(err){
        console.log(err);
        setMessage("Registraion Failed");
    }
  }

  return (
    <div>
      <form className="register-form" onSubmit={handleSubmit}>
        <label>Enter Name</label>
        <input
          type="text"
          name="name"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <label>Enter email</label>
        <input
          type="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <label>Enter phone number</label>
        <input
          type="number"
          name="phoneNumber"
          value={formData.phoneNumber}
          onChange={handleChange}
          required
        />
        <label>Choose gender</label>
        <div>
          <input
            type="radio"
            name="gender"
            value="male"
            checked={formData.gender == "male"}
            onChange={handleChange}
            required
          />
          <label>Male</label>
          <input
            type="radio"
            name="gender"
            value="female"
            checked={formData.gender == "female"}
            onChange={handleChange}
            required
          />
          <label>Female</label>
        </div>

        <label>Enter password</label>
        <input
          type="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <label>Confirm password</label>
        <input
          type="password"
          name="confirmPassword"
          value={formData.confirmPassword}
          onChange={handleChange}
          required
        />
        <button type="submit">Register</button>
        {message && <p>{message}</p>}
      </form>
    </div>
  );
}
export default RegisterForm;
