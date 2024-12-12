import React, { useContext } from "react";
import UserContext from '../../contexts/userContext';
import Logout from '../../components/Forms/Logout.jsx';

import Helmet from "../../components/Helmet/Helmet.jsx";

const LogoutPage = () => {

    const { logout } = useContext(UserContext);

    return (
        <Helmet title="Logout">
            <Logout onLogout={logout} />
        </Helmet>
        
    );
}

export default LogoutPage;