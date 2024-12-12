import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import LogoutPage from "../pages/forms/LogoutPage";
import Home from "../pages/Home";
import SenderPage from '../pages/forms/SenderPage';
import ReceiverPage from '../pages/forms/ReceiverPage';
import AddPackagePage from '../pages/forms/AddPackagePage';
import AddPaymentPage from "../pages/forms/AddPaymentPage";
import Packages from "../pages/Packages.jsx";
import PackageDetail from "../pages/PackageDetail";
import Chat from "../pages/Chat";
import UserProfile from "../pages/UserProfile.jsx";
import Footer from "../components/Footer/Footer";
import NotificationsPortal from "../pages/NotificationsPortal.jsx";
import PaymentConfirmationPage from "../pages/forms/PaymentConfirmationPage.jsx";

const ClientRouters = () => {
  return (
    <div className="page-wrapper">
      <div className="content-wrapper">
        <Routes>
          <Route path="/logout" element={<LogoutPage />} />
          <Route path="/" element={<Navigate to="/client" />} />
          <Route path="/login" element={<Navigate to="/client" />} />
          <Route path="/client" element={<Home />} />
          <Route path="/add-client" element={<Navigate to="/client" />} />
          <Route path="/add-driver" element={<Navigate to="/client" />} />
          <Route path="/package-request" element={<Navigate to="/sender" />} />
          <Route path="/sender" element={<SenderPage />} />
          <Route path="/receiver" element={<ReceiverPage />} />
          <Route path="/add-item" element={<AddPackagePage />} />
          <Route path="/add-payment" element={<AddPaymentPage />} />
          <Route path="/confirmation" element={<PaymentConfirmationPage />} />
          <Route path="*" element={<Navigate to="/client" />} />
          <Route path="/packages" element={<Packages fetchPackagesCriteria="Client"/>} />
          <Route path="/package-detail" element={<PackageDetail />} />
          <Route path="/chat" element={<Chat />} />
          <Route path="/profile" element={<UserProfile />} />
          <Route path="/notifications" element={<NotificationsPortal />} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
};

export default ClientRouters;
