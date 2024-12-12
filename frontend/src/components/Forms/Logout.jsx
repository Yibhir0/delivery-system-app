import React from "react";
import FormWrapper from '../Elements/FormWrapper';

import { useNavigate } from "react-router-dom";

const Logout = ({ onLogout }) => {

    const navigate = useNavigate();
    const out = (e) => {
        e.preventDefault();
        onLogout();
        navigate('/');
    };
    return (
        <FormWrapper title="Logout" onSubmit={out}>
            <button type="submit">Logout</button>
        </FormWrapper>
    );
};

export default Logout;
