
import React, { useState } from 'react';

import FormWrapper from '../Elements/FormWrapper';
import TextInput from '../Elements/TextInput';


const AddLocationForm = ({ type, onSubmit, data, updateData }) => {

    let typeOfLocation;
    if (type === 'senderAddress') {
        typeOfLocation = 'Sender';
    } else if (type === 'receiverAddress') {
        typeOfLocation = 'Receiver';
    }
    else {
        typeOfLocation = 'Warehouse';
    }


    const handleChange = (e) => {
        const { name, value } = e.target;

        let fieldName;
        if (name === 'locationNumber') {
            fieldName = `${type}.number`;
        } else if (name === 'locationStreet') {
            fieldName = `${type}.street`;
        } else if (name === 'locationCity') {
            fieldName = `${type}.city`;
        } else if (name === 'locationCountry') {
            fieldName = `${type}.country`;
        } else if (name === 'locationZipCode') {
            fieldName = `${type}.zipCode`;
        }

        // Update form data using the dynamic key
        if (updateData) updateData(fieldName, value);
        // updateData(name, value);
    };


    const handleSubmit = (e) => {
        e.preventDefault();
        if (onSubmit) onSubmit();
        alert('Location Added');

    };

    return (
        <FormWrapper title="Add Location" onSubmit={handleSubmit}>
            <h3>{typeOfLocation} Information</h3>
            <TextInput
                type='number'
                label="Number"
                name="locationNumber"
                value={data?.number}
                onChange={handleChange}>
            </TextInput>
            <TextInput
                type='text'
                label="Street"
                name="locationStreet"
                value={data?.street}
                onChange={handleChange}>
            </TextInput>

            <TextInput
                type='text'
                label="City"
                name="locationCity"
                value={data?.city}
                onChange={handleChange}>
            </TextInput>

            <TextInput
                type='text'
                label="Country"
                name="locationCountry"
                value={data?.country}
                onChange={handleChange}>
            </TextInput>

            <TextInput
                type='text'
                label="Zip Code"
                name="locationZipCode"
                value={data?.zipCode}
                onChange={handleChange}>
            </TextInput>

            <button type="submit">{`Add  Location`}</button>
        </FormWrapper>
    );
};


export default AddLocationForm;
