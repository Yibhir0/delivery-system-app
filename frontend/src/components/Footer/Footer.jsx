import React from "react";
import { ListGroup } from "reactstrap";

import logo from "../../assets/images/parcel-log.png";
import "../../styles/footer.css";

const Footer = () => {
  // TODO: Fix the footer because it is not displaying correctly and hiding the content
  return (
    <footer className="footer">
      <div className="footer__logo">
        <img src={logo} alt="logo" />
        <h5>Habibi Deliveries</h5>
        <p>Get the best deliveries, shipped to you!</p>
      </div>
      <div>
        <h5 className="footer__title mb-3">Delivery Time</h5>
        <ListGroup>
          <div className="delivery__time-item border-0 ps-0">
            <span>Monday - Friday</span>
            <p>8:00am - 10:00pm</p>
          </div>
          <div className="delivery__time-item border-0 ps-0">
            <span>Saturday - Sunday</span>
            <p>Closed</p>
          </div>
        </ListGroup>
      </div>
    </footer>
  );
};

export default Footer;
