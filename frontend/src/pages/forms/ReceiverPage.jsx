import React, { useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import LocationForm from '../../components/Forms/AddLocationForm';
import FormContext from '../../contexts/formContext';
const ReceiverPage = () => {
    const { formData, updateFormData } = useContext(FormContext);

    console.log('formData', formData);

    const navigate = useNavigate();
    return (
        <LocationForm type="receiverAddress" onSubmit={() => navigate('/add-item')} data={formData} updateData={updateFormData} />
    );
};

export default ReceiverPage;
