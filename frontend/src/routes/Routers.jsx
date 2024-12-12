import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";

import Home from "../pages/Home";

import LoginPage from "../pages/forms/LoginPage";

import AddPackagePage from "../pages/forms/AddPackagePage";

import Chat from "../pages/Chat";


import Packages from "../pages/Packages.jsx";
import AddUserPage from "../pages/forms/AddUserPage.jsx";

const Routers = () => {
  return (
    <Routes>

      <Route path="/packages" element={<Packages />} />
      <Route path="/add-item" element={<AddPackagePage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/signup" element={<AddUserPage />} />
      <Route path="/chat" element={<Chat />} />

    </Routes>
  );
};

export default Routers;
