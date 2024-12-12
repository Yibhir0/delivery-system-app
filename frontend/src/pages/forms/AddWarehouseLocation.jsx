import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import LocationForm from '../../components/Forms/AddLocationForm';


import { addWarehouseToCompany } from '../../api/post/PostDataService'

const AddWarehouseLocation = () => {

    const Navigate = useNavigate();

    const [formData, setFormData] = useState({
        warehouseLocation: {
            number: 0,
            street: '',
            city: '',
            country: '',
            zipCode: '',
        }

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

    const handleSubmit = async () => {

        const data = formData.warehouseLocation;
        await addWarehouseToCompany(data);
        alert("Warehouse Location Added Successfully");
        Navigate("/admin");
    }


    return (
        <LocationForm type="warehouseLocation" onSubmit={handleSubmit} data={formData} updateData={updateFormData} />
    );
};

export default AddWarehouseLocation;
