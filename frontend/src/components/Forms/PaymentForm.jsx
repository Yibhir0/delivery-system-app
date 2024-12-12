import React, { useState } from "react";
import "../../styles/payment-form.css";

const PaymentForm = ({ onSubmit }) => {
  const [activeTab, setActiveTab] = useState("creditCard"); // Default tab is Credit Card
  const [amount] = useState(
    (Math.random() * (49.99 - 10.01) + 10.01).toFixed(2) // Static amount generated once
  );

  const handleTabChange = (tab) => {
    setActiveTab(tab);
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    onSubmit(); // Call the passed onSubmit prop when the form is submitted
  };

  return (
    <div className="payment-form-wrapper">
      {/* Header */}
      <h1 className="payment-header">Secure Payment</h1>

      {/* Tabs for Payment Methods */}
      <div className="payment-tabs">
        <div
          className={`tab ${activeTab === "creditCard" ? "active" : ""}`}
          onClick={() => handleTabChange("creditCard")}
        >
          Credit Card
        </div>
        <div
          className={`tab ${activeTab === "paypal" ? "active" : ""}`}
          onClick={() => handleTabChange("paypal")}
        >
          PayPal
        </div>
      </div>

      {/* Display the Payment Amount */}
      <div className="amount-display">
        <h2>Total Amount: ${amount}</h2>
      </div>

      {/* Payment Form */}
      <form className="payment-form" onSubmit={handleSubmit}>
        <h3>{activeTab === "creditCard" ? "Credit Card Payment" : "PayPal Payment"}</h3>

        <div className="form-grid">
          {/* Common Fields */}
          <div className="form-group">
            <label htmlFor="firstName">First Name</label>
            <input id="firstName" type="text" placeholder="John" required />
          </div>
          <div className="form-group">
            <label htmlFor="lastName">Last Name</label>
            <input id="lastName" type="text" placeholder="Doe" required />
          </div>

          {/* Credit Card Fields */}
          {activeTab === "creditCard" && (
            <>
              <div className="form-group">
                <label htmlFor="cardNumber">Card Number</label>
                <input
                  id="cardNumber"
                  type="text"
                  placeholder="1234 5678 9012 3456"
                  required
                />
              </div>
              <div className="form-group">
                <label htmlFor="expiryDate">Expiry Date</label>
                <input id="expiryDate" type="text" placeholder="MM/YY" required />
              </div>
              <div className="form-group">
                <label htmlFor="cvv">CVV</label>
                <input id="cvv" type="text" placeholder="123" required />
              </div>
            </>
          )}

          {/* PayPal Fields */}
          {activeTab === "paypal" && (
            <div className="form-group">
              <label htmlFor="paypalEmail">PayPal Email</label>
              <input
                id="paypalEmail"
                type="email"
                placeholder="example@paypal.com"
                required
              />
            </div>
          )}
        </div>

        {/* Submit Button */}
        <div className="form-group">
          <button type="submit" className="payment-btn">
            Submit Payment
          </button>
        </div>
      </form>
    </div>
  );
};

export default PaymentForm;
