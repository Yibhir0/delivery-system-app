import React, { useContext } from "react";



import PublicRouters from "../../routes/PublicRouters";

import { public__links } from "../../constants/userLinks";

import Header from "../../components/Header/Header";

const PublicApp = () => {


    return (
        <div>
            <Header nav__links={public__links} />
            <PublicRouters />
        </div>
    );
}

export default PublicApp;
