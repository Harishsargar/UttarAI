import "../style/pricing.css"
import { useNavigate, Link } from "react-router-dom";
import React, { useEffect, useState } from 'react';
import Navbar from "./Navbar";
import { createOrder, fetchCurrentPlan, verifyPayment } from "../api/payment";
// import './Pricing.css'; // Optional external CSS if you're not using Tailwind

const Pricing = () => {

    const navigate = useNavigate();
    const [currentPlan, setCurrentPlan] = useState("No Data");
    const [apiCallsLeft, setApiCallsLeft] = useState("No Data");



    const fetchPlan = async () => {
        try {
            const response = await fetchCurrentPlan();
            setCurrentPlan(response?.data?.plan);
            setApiCallsLeft(response?.data?.apiCallsLeft + " Api Calls left");
        } catch (error) {
            console.error("Error fetching current plan:", error);
        }
    };

    useEffect(() => {
        fetchPlan();
    }, []);


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

    const handlePayment = async () => {
        if (!localStorage.getItem("token")) {
            navigate("/login");
        }
        try {
            const order = "Basic Plan"
            const response = await createOrder({ order })
            if (response.status != 201) {
                throw new Error("Order not created");
            }

            var options = {
                "key": "rzp_test_re44xLAcI84GUD", // Enter the Key ID generated from the Dashboard
                "amount": "50000", // Amount is in currency subunits. 
                "currency": "INR",
                "name": "Uttar-AI", //your business name
                "description": "Test Transaction",
                "image": "https://example.com/your_logo",
                "order_id": response.data.id, // This is a sample Order ID. Pass the `id` obtained in the response of Step 1
                "callback_url": "https://eneqd3r9zrjok.x.pipedream.net/",
                "prefill": { //We recommend using the prefill parameter to auto-fill customer's contact information especially their phone number
                    "name": "Gaurav Kumar", //your customer's name
                    "email": "gaurav.kumar@example.com",
                    "contact": "+91121212121" //Provide the customer's phone number for better conversion rates 
                },
                "notes": {
                    "address": "Razorpay Corporate Office"
                },
                "theme": {
                    "color": "#1e43e8ff"
                },
                "modal": {
                    ondismiss: function () {
                        console.log("Payment cancelled or failed");
                        alert("Payment cancelled by the user or failed. \nPlease try again.");
                    }
                },
                "handler": function (response) {

                    // console.log(response.razorpay_payment_id);
                    // console.log(response.razorpay_order_id);
                    // console.log(response.razorpay_signature);
                    console.log("payment success from razorpay");
                    verifyPaymentAtBackend(
                        response.razorpay_payment_id,
                        response.razorpay_order_id,
                        response.razorpay_signature
                    );
                    // alert("Payment Successful");
                },
            };


            var rzp1 = new Razorpay(options);
            // 🔴 Handle real payment failures
            rzp1.on('payment.failed', function (response) {
                console.error("❌ Payment Failed", response.error);
                alert("Payment Failed!\nReason: " + response.error.description);
            });


            rzp1.open();


        } catch (error) {
            console.log(error)
            alert("something went wrong");
        }
    }


    async function verifyPaymentAtBackend(razorpay_payment_id, razorpay_order_id, razorpay_signature) {
        try {
            // send the details to the backend
            const razorpayPaymentId = razorpay_payment_id;
            const razorpayOrderId = razorpay_order_id;
            const razorpaySignature = razorpay_signature;
            const response = await verifyPayment({ razorpayPaymentId, razorpayOrderId, razorpaySignature });
            if (response.status == 202) {
                fetchPlan();
                alert("Payment SuccessFull");
            } else if (response.status == 406) {
                alert("payment Not Done\nIf money debited will be creadited back in 3-4 days")
            } else {
                alert("Something went wrong\n If money debited will be creadited back in 3-4 days")
            }
        } catch (error) {
            // if error at verifying send alert "their was error while cpturing your payment on our serer"
            alert("Error While Proccessing Payment\n If money debited will be creadited back in 3-4 days")
        }
    }


    return (
        <>
            <Navbar /><div style={{ paddingTop: '60px' }}></div>

            <div>
                <div className="currentplan">
                    <h3>Current Active Plan : <span>{currentPlan}</span> </h3>
                    <h3>Api Calls Left : <span>{apiCallsLeft}</span></h3>
                </div>
            </div>

            <div className="pricing">
                {plans.map((plan, index) => (
                    <div key={index} className="pricingcard ">
                        <h6 className="cardheading">{plan.name}</h6>
                        <h1>₹ {plan.price}</h1>
                        <ul><br />
                            {plan.features.map((feature, idx) => (
                                <li key={idx} >{feature}</li>
                            ))}
                        </ul>
                        <button onClick={handlePayment}>
                            Choose Plan
                        </button>
                    </div>
                ))}
            </div>
        </>
    );
};

export default Pricing;
