import React, { useContext, useEffect } from 'react';

import AddPackageForm from '../../components/Forms/AddPackageForm';

import Helmet from "../../components/Helmet/Helmet.jsx";

// import './AddPackagePage.css';

import { useNavigate } from 'react-router-dom';

import FormContext from '../../contexts/formContext.jsx';

const AddPackagePage = () => {


    const { submitForm, updateFormData } = useContext(FormContext);



    useEffect(() => {
        const savedUser = JSON.parse(localStorage.getItem("user"));

        const { id } = savedUser;

        updateFormData('clientId', id);
    }
        , []);


    const navigate = useNavigate();
    const handleSubmit = async () => {

        console.log('Form Submitted');
        const deliveryRequest = await submitForm();
        // save to local storage under the name currentTransation
        localStorage.setItem("currentTransaction", deliveryRequest);
        navigate('/add-payment');


    };

    // const onAddPackage = async (p) => {
    //     try {
    //         const data = await addPackage(p);
    //         console.log(data);


    //     } catch (error) {
    //         console.error('Error :', error);

    //     }
    // };

    return (
        <Helmet title="Add Package">
            <AddPackageForm onSubmit={handleSubmit} type={"packageDetails"} />
        </Helmet>
    );
};

export default AddPackagePage;