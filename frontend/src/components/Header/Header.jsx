import React, { useRef, useEffect } from "react";
import { useNavigate } from "react-router-dom";

import { Container } from "reactstrap";
import logo from "../../assets/images/parcel-log.png";
import { NavLink, Link } from "react-router-dom";




import "../../styles/header.css";

// const nav__links = [
//   {
//     display: "Home",
//     path: "/home",
//   },
//   {
//     display: "Our Rates",
//     path: "/#",
//   },
//   {
//     display: "Track a Package",
//     path: "/packages",
//   },
//   {
//     display: "Contact",
//     path: "/cart",
//   },


//   {
//     display: "Add Package",
//     path: "/add-item",
//   },

//   {
//     display: "Add Driver",
//     path: "/add-driver",
//   },

//   {
//     display: "Signup",
//     path: "/signup",
//   },

//   {
//     display: "Login",
//     path: "/login",
//   },


//    {
//      display: "Chat",
//      path: "/chat",
//    },
//  ];

const Header = ({ nav__links }) => {
  const menuRef = useRef(null);
  const headerRef = useRef(null);

  let navigate = useNavigate();


  return (
    <header className="header" ref={headerRef}>
      <Container>
        <div className="nav__wrapper d-flex align-items-center justify-content-between">
          <div className="logo" onClick={() => navigate("/home")}>
            <img src={logo} alt="logo" />
            <h5>Habibi Deliveries</h5>
          </div>

          <div className="navigation" ref={menuRef} >
            <div
              className="menu d-flex align-items-center gap-5"
              onClick={(event) => event.stopPropagation()}
            >
              <div className="header__closeButton">
                <span >
                  <i className="ri-close-fill"></i>
                </span>
              </div>
              {nav__links.map((item, index) => (
                <NavLink
                  to={item.path}
                  key={index}
                  className={(navClass) =>
                    navClass.isActive ? "active__menu" : ""
                  }

                >
                  {item.display}
                </NavLink>
              ))}
            </div>
          </div>

          {/* ======== nav right icons ========= */}
          <div className="nav__right d-flex align-items-center gap-4">

            <span className="mobile__menu" >
              <i className="ri-menu-line"></i>
            </span>
          </div>
        </div>
      </Container>
    </header>
  );
};

export default Header;
