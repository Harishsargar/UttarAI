import "../style/pricing.css"
import { useNavigate, Link } from "react-router-dom";
import React from 'react';
import Navbar from "./Navbar";
import { createOrder } from "../api/payment";
// import './Pricing.css'; // Optional external CSS if you're not using Tailwind

const Pricing = () => {

    const navigate = useNavigate();

    const plans = [
        {
            name: 'Basic Plan',
            price: 50,
            features: ['Get 200 API Calls', 'Email Support', 'Limited Access'],
        },
        {
            name: 'Pro Plan --(comming soon)',
            price: 299,
            features: ['Get 1000 API Calls', 'Priority Email Support', 'Access to Beta Features'],
        },
        {
            name: 'Enterprise Plan (comming soon)',
            price: 999,
            features: ['Unlimited API Calls', 'Dedicated Support', 'Custom Integration'],
        },
    ];

    const handlePayment= async () =>{
        if(!localStorage.getItem("token")){
            navigate("/login");
        }
        try {
            const order  = "Basic Plan"
            const response = await createOrder({order})
        } catch (error) {
            console.log(error)
            alert("something went wrong");
        }
    }

    return (
        <>
        <Navbar/><div style={{ paddingTop: '60px' }}></div>
        <div className="pricing grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6 p-6">
            {plans.map((plan, index) => (
                <div key={index} className="pricingcard border rounded-2xl p-6 shadow-lg hover:shadow-xl transition">
                    <h2 className="cardheading text-xl font-bold mb-2">{plan.name}</h2>
                    <h1 className="text-3xl font-semibold text-green-600 mb-4">₹ {plan.price}</h1>
                    <ul className="mb-4 list-disc list-inside"><br/>
                        {plan.features.map((feature, idx) => (
                            <li key={idx} className="text-gray-700">{feature}</li>
                        ))}
                    </ul>
                    <button onClick={handlePayment} className="bg-green-600 text-white px-4 py-2 rounded-xl hover:bg-green-700 transition">
                        Choose Plan
                    </button>
                </div>
            ))}
        </div>
        </>
    );
};

export default Pricing;
