import React, { useContext } from "react";

import ClientRouters from "../../routes/ClientRouters";

import { client__links } from "../../constants/clientLinks";

import Header from "../../components/Header/Header";

import { FormProvider } from '../../contexts/formContext';

import "../../styles/public-ui.css"

const ClientApp = () => {

    return (
        <FormProvider>
            <Header nav__links={client__links} />
            <ClientRouters />
        </FormProvider>
    );
}

export default ClientApp;