import React from 'react';
import { useNavigate } from 'react-router-dom';
import LocationForm from '../../components/Forms/AddLocationForm';
import PaymentForm from '../../components/Forms/PaymentForm';

const AddPaymentPage = () => {
    const navigate = useNavigate();

    const handlePaymentSubmit = () => {
        navigate('/confirmation'); // Redirect to the confirmation page on successful payment submission
    };

    return (
        <div>
            <PaymentForm onSubmit={handlePaymentSubmit} />
        </div>
    );
};

export default AddPaymentPage;
