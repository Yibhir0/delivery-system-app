import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import LocationForm from '../../components/Forms/AddLocationForm';

import FormContext from '../../contexts/formContext';

const SenderPage = () => {

    const { formData, updateFormData } = useContext(FormContext);
    console.log('formData', formData);
    const navigate = useNavigate();
    return (
        <LocationForm type="senderAddress" onSubmit={() => navigate('/receiver')} data={formData} updateData={updateFormData} />
    );
};

export default SenderPage;