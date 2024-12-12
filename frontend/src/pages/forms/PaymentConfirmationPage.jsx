import React from "react";
import "../../styles/payment-confirmation-page.css";
import { useLocation, useNavigate } from "react-router-dom";  // Import useNavigate



const ConfirmationPage = () => {
  const location = useLocation();
  const navigate = useNavigate();

  // Retrieving query parameters for the confirmation page, even though they won't be displayed
  const queryParams = new URLSearchParams(location.search);
  const paymentMethod = queryParams.get("paymentMethod");
  const amount = queryParams.get("amount");

  return (
    <div className="confirmation-container">
      <div className="confirmation-card">
        <h1 className="confirmation-header">Payment Confirmation</h1>
        <p className="confirmation-message">
          <span className="highlight-text">Thank you for your payment!</span>
        </p>
        <p className="confirmation-text">
          Your payment was successfully processed. We appreciate your trust!
        </p>
        <button
          className="go-home-btn"
          onClick={() => navigate("/client")} // You can replace '/home' with your desired path
        >
          Go to Homepage
        </button>
      </div>
    </div>
  );
};

export default ConfirmationPage;