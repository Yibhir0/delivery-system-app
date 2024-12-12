import React from "react";
import Helmet from "../components/Helmet/Helmet.jsx";
import { Container, Row, Col } from "reactstrap";
import { Link } from "react-router-dom";

import guyImg from "../assets/images/truck.gif"; // Image of delivery truck
import "../styles/hero-section.css"; // Custom styles

const Home = () => {
  return (
    <Helmet title="Home">
      <section className="hero">
        <Container>
          <Row className="align-items-center">
            {/* Hero Content Section */}
            <Col lg="6" md="6" className="text-center text-md-left">
              <div className="hero__content">
                <h5 className="subheading">Fast, Reliable & Secure</h5>
                <h1 className="mb-4 hero__title">
                  <span>Trust</span> Us to Deliver Your Packages Safely and On Time
                </h1>
                <p className="hero__desc">
                  Whether you're shipping locally or internationally, we have the
                  perfect solution for your delivery needs. Explore our services
                  now.
                </p>

                <div className="cta-buttons">
                  <button className="order__btn">
                    <Link to="/package-request">
                      Request a Quote <i className="ri-arrow-right-s-line"></i>
                    </Link>
                  </button>
                  <button className="learn-more__btn">
                    <Link to="/#">
                      Learn More <i className="ri-arrow-right-s-line"></i>
                    </Link>
                  </button>
                </div>
              </div>
            </Col>

            {/* Hero Image Section */}
            <Col lg="6" md="6" className="hero__img-container">
              <div className="hero__img">
                <img src={guyImg} alt="delivery-guy" className="w-100" />
              </div>
            </Col>
          </Row>

          {/* Client Features Section */}
          <Row className="features-section mt-5">
            <Col md="4" className="feature-card">
              <div className="feature-icon">
                <i className="ri-truck-line"></i>
              </div>
              <h3>Fast Delivery</h3>
              <p>We guarantee fast delivery times for all your packages.</p>
            </Col>
            <Col md="4" className="feature-card">
              <div className="feature-icon">
                <i className="ri-secure-payment-line"></i>
              </div>
              <h3>Secure Transactions</h3>
              <p>All transactions are encrypted and secure for your peace of mind.</p>
            </Col>
            <Col md="4" className="feature-card">
              <div className="feature-icon">
                <i className="ri-map-pin-2-line"></i>
              </div>
              <h3>Real-Time Tracking</h3>
              <p>Track your shipment in real time with our advanced tracking system.</p>
            </Col>
          </Row>

        </Container>
      </section>
    </Helmet>
  );
};

export default Home;
