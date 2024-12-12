import React, { useContext } from "react";

import UserContext from "../../contexts/userContext";

import AdminRouters from "../../routes/AdminRouters";

import { admin__links } from "../../constants/adminLinks";

import Header from "../../components/Header/Header";
import {FormProvider} from "../../contexts/formContext.jsx";


const AdminApp = () => {

    return (
        <FormProvider>
            <Header nav__links={admin__links} />
            <AdminRouters />
        </FormProvider>
    );
}

export default AdminApp;