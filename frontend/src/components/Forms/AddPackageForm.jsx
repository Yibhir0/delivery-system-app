// AddItemForm.js
import React, { useState, useContext } from 'react';
import FormWrapper from '../Elements/FormWrapper';
import TextInput from '../Elements/TextInput';

import SelectInput from '../Elements/SelectInput';

import { package__types } from '../../constants/packageType';

import FormContext from '../../contexts/formContext';

import { useNavigate } from 'react-router-dom';


const AddItemForm = ({ type, onSubmit }) => {

    const { formData, updateFormData } = useContext(FormContext);

    const Navigate = useNavigate();


    console.log('formData', formData);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        let fieldName;
        if (name === 'itemWidth') {
            fieldName = `${type}.width`;
        };
        if (name === 'itemHeight') {
            fieldName = `${type}.height`;
        }
        if (name === 'itemLength') {
            fieldName = `${type}.length`;
        }
        if (name === 'itemWeight') {
            fieldName = `${type}.weight`;
        }

        if (name === 'itemType') {
            fieldName = `${type}.packageType`;
        }

        if (updateFormData) updateFormData(fieldName, value);

    };

    const handleSubmit = (e) => {


        e.preventDefault();
        if (onSubmit) onSubmit();
        Navigate('/add-payment')
        alert('Package Request is Added');
    };

    return (
        <FormWrapper title="Add Package" onSubmit={handleSubmit}>
            <TextInput
                type='number'
                label="Width"
                name="itemWidth"
                value={formData?.width}
                onChange={handleInputChange}

            />

            <TextInput
                type='number'
                label="Height"
                name="itemHeight"
                value={formData?.height}
                onChange={handleInputChange}

            />

            <TextInput
                type='number'
                label="Length"
                name="itemLength"
                value={formData?.length}
                onChange={handleInputChange}

            />

            <TextInput
                type='number'
                label="Weight"
                name="itemWeight"
                value={formData?.weight}
                onChange={handleInputChange}


            />
            <SelectInput
                label="Package Type"
                name="itemType"
                value={formData?.packageType}
                onChange={handleInputChange}
                options={package__types}
            />

            <button type="submit">Submit Request</button>
        </FormWrapper>
    );
};


export default AddItemForm;
