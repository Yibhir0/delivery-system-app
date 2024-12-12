
import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import LogoutPage from "../pages/forms/LogoutPage";
import UserProfile from "../pages/UserProfile.jsx";
import Footer from "../components/Footer/Footer.jsx";
import CompanyProfile from "../pages/CompanyProfile.jsx";

import AddWarehouseLocation from "../pages/forms/AddWarehouseLocation.jsx";

const AdminRouters = () => {
    return (
        <div className="page-wrapper">
            <div className="content-wrapper">
                <Routes>
                    <Route path="/" element={<Navigate to="/admin" />} />
                    <Route path="/profile" element={<UserProfile />} />
                    <Route path="/admin" element={<CompanyProfile />} />
                    <Route path="/logout" element={<LogoutPage />} />
                    <Route path="/*" element={<Navigate to="/admin" />} />
                    <Route path="/add-warehouse" element={<AddWarehouseLocation />} />
                </Routes>
            </div>
            <Footer />
        </div>
    );
};

export default AdminRouters;
