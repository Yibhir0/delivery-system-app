import React from "react"; // Import React
import { Link } from "react-router-dom"; // Import Link for navigation
import "../styles/public-ui.css"; // Import the CSS file

import avaImage from "../assets/images/users/ava-1.jpg";  // Import the image
import botLogo from "../assets/images/bot-icon.png"; // Import the Bot Icon for the button

const PublicUI = () => {
  return (
    <div className="public-ui">
      {/* Hero Section */}
      <section className="hero">
        <div className="hero-content">
          <h1>Shipping Services at a Glance</h1>
          <p>
            It doesn’t matter whether you ship a little or a lot. Habibi Deliveries
            offers a variety of shipping choices to meet your changing needs.
          </p>
          <Link to="/login" className="btn-primary">
            Ship Now
          </Link>
        </div>
        <div className="hero-image">
          {/* Placeholder for a background image or visual */}
          <img
            src={avaImage}
            alt="Delivery Service"
          />
        </div>
      </section>

      {/* Secondary Section */}
      <section className="features">
        <h2>Consolidate Your Shipping with DeliveryApp</h2>
        <p>
          Whether you are shipping across town or around the world, we can help
          you find solutions to fit any need, budget, or schedule.
        </p>
      </section>

      {/* Ask Habibi Bot Button */}
      <Link to="/chat" className="ask-habibi-btn">
        <img src={botLogo} alt="Habibi Bot" className="bot-logo" />
        Ask Habibi Bot
      </Link>
    </div>
  );
};

export default PublicUI;
