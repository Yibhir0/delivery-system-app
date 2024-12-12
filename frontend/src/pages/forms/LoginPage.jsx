import React, { useContext } from 'react';

import LoginForm from '../../components/Forms/LoginForm';

import UserContext from '../../contexts/userContext';

import Helmet from "../../components/Helmet/Helmet.jsx";

import { logIn } from '../../api/post/PostDataService.js';

import useAppNavigator from '../../hooks/useAppNavigator.jsx';

// import { useNavigate } from 'react-router-dom';

// import App from '../../App.jsx';

const LoginPage = () => {

    const { login } = useContext(UserContext);

    const navigateTo = useAppNavigator();

    const onLogin = async (u) => {
        try {

            const data = await logIn(u);

            const { id, first_name, last_name, email, password, userType } = data;

            login(id, first_name, last_name, email, password, userType);
            alert(' Logged In');
            navigateTo(userType);
        } catch (error) {
            if (error.response && error.response.status === 401) {
                alert('Unauthorized: Invalid credentials');
            } else {
                console.error('Error:', error);
            }
        }

    };
    return (
        <Helmet title="Login">
            <LoginForm onLogin={onLogin} />
        </Helmet>
    );
};

export default LoginPage;