import React, { useContext } from "react";
import UserContext from "./contexts/userContext";
import Layout from "./components/Layout/Layout";

import AdminApp from "./pages/apps/AdminApp";
import ClientApp from "./pages/apps/ClientApp";
import DriverApp from "./pages/apps/DriverApp";
import PublicApp from "./pages/apps/PublicApp";

import "./index.css"



function App() {

  const { user } = useContext(UserContext);


  const renderApp = () => {

    if (user.userType === "ADMIN") {
      return <AdminApp />;
    }
    else if (user.userType === "CLIENT") {
      return <ClientApp />;
    }
    else if (user.userType === "DRIVER") {
      return <DriverApp />;
    }
    else {
      return <PublicApp />;

      // return <Layout />;
    }
  }

  return (
    <div>

      {renderApp()}
      {/* <div>
        <Routes />
      </div> */}
    </div>
  );

}

export default App;
