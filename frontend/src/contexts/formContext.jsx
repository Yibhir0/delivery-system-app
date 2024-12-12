import React, { createContext, useState } from 'react';
import { createDeliveryRequest } from '../api/post/PostDataService';



const FormContext = createContext(
    {

        clientId: 0,
        senderAddress: {
            number: 0,
            street: '',
            city: '',
            country: '',
            zipCode: '',
        },
        receiverAddress: {
            number: 0,
            street: '',
            city: '',
            country: '',
            zipCode: '',
        },
        packageDetails: {
            width: 0,
            height: 0,
            length: 0,
            weight: 0,
            packageType: "FRAGILE",
        },



    }
);


export const FormProvider = ({ children }) => {


    const [formData, setFormData] = useState({

        clientId: 0,
        senderAddress: {
            number: 0,
            street: '',
            city: '',
            country: '',
            zipCode: '',
        },
        receiverAddress: {
            number: 0,
            street: '',
            city: '',
            country: '',
            zipCode: '',
        },
        packageDetails: {
            width: 0,
            height: 0,
            length: 0,
            weight: 0,
            packageType: "FRAGILE",
        },
    });

    const updateFormData = (key, value) => {


        setFormData((prev) => {
            // Check if the key is for a nested object (i.e., contains a dot)
            if (key.includes('.')) {
                const keys = key.split('.'); // Split the key into parts, like ['senderAddress', 'number']

                // Update the nested field
                return {
                    ...prev,
                    [keys[0]]: {
                        ...prev[keys[0]],
                        [keys[1]]: value, // Update the nested field
                    },
                };
            }
            // If no dot, update the top-level field
            return { ...prev, [key]: value };
        });
    };

    const submitForm = () => {
        try {


            console.log('Form Data:', formData);
            const data = createDeliveryRequest(formData);
            console.log(data);
        } catch (error) {
            console.error('Error :', error);
        }
    };

    return (
        <FormContext.Provider value={{ formData, updateFormData, submitForm }}>
            {children}
        </FormContext.Provider>
    );
};

export default FormContext;
