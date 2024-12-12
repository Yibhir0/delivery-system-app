import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import LoginPage from "../pages/forms/LoginPage";
import PublicUI from "../pages/PublicUI";
import Chat from "../pages/Chat";
import Footer from "../components/Footer/Footer";
import AddUserPage from "../pages/forms/AddUserPage.jsx";

const PublicRouters = () => {
    return (
        <div className="page-wrapper">
            <div className="content-wrapper">
                <Routes>
                    <Route path="/" element={<PublicUI />} />
                    <Route path="/home" element={<PublicUI />} />
                    <Route path="/login" element={<LoginPage />} />
                    <Route path="/signup" element={<AddUserPage />} />
                    <Route path="/chat" element={<Chat />} />
                </Routes>
            </div>
            <Footer />
        </div>
    );
};

export default PublicRouters;
