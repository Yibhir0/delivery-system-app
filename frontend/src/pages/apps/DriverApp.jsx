import React, { useContext } from "react";

import UserContext from "../../contexts/userContext";

import DriverRouters from "../../routes/DriverRouters";

import { driver__links } from "../../constants/driverLinks";

import Header from "../../components/Header/Header";
import {FormProvider} from "../../contexts/formContext.jsx";

const DriverApp = () => {


    return (
        <FormProvider>
            <Header nav__links={driver__links} />
            <DriverRouters />
        </FormProvider>
    );
}

export default DriverApp;