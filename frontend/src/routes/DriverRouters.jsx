import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import LogoutPage from "../pages/forms/LogoutPage";
import UserProfile from "../pages/UserProfile.jsx";
import Footer from "../components/Footer/Footer.jsx";
import Packages from "../pages/Packages.jsx";
import PackageDetail from "../pages/PackageDetail.jsx";

const DriverRouters = () => {
    return (
        <div className="page-wrapper">
            <div className="content-wrapper">
                <Routes>
                    <Route path="/" element={<Navigate to="/inventory" />} />
                    <Route path="/inventory" element={<Packages fetchPackagesCriteria="Assigned" />} />
                    <Route path="/assign-package" element={<Packages fetchPackagesCriteria="Unassigned" />} />
                    <Route path="/package-detail" element={<PackageDetail />} />
                    <Route path="/profile" element={<UserProfile />} />
                    <Route path="/logout" element={<LogoutPage />} />
                    <Route path="/*" element={<Navigate to="/inventory" />} />
                </Routes>
            </div>
            <Footer />
        </div>
    );
};

export default DriverRouters;
