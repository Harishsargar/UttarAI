import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import Navbar from "./Navbar";
import Hero from "./Hero";
import Usecase from "./Usecase";
import Featuers from "./Featuers";
import Testimonials from "./Testimonials";
import Instructions from "./Instructions";

function Home() {
  const [message, setMessage] = useState("");

  const handleLogout = () => {
    localStorage.removeItem("token");
    setMessage("User logout successfully !!");
  };

  return (
    <>
    <Navbar/>
    <div style={{ paddingTop: '60px' }}></div>
    <Hero/>
    <Usecase/>
    <Instructions/>
    <Featuers/>
    

    </>
  );
}

export default Home;
