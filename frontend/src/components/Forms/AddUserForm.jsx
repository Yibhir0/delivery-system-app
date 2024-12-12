// AddItemForm.js
import React, { useState } from 'react';
import FormWrapper from '../Elements/FormWrapper';
import TextInput from '../Elements/TextInput';
import '../../styles/tab.css';

const AddUserForm = ({ onAddClient, onAddDriver }) => {

    const [activeTab, setActiveTab] = React.useState('SignUpClient');

    const handleTabClick = (tabName) => {
        setActiveTab(tabName);
    };


    const [userData, setUserData] = useState({

        first_name: "",
        last_name: "",
        email: "",
        password: "",
        userType: ""
    });

    const handleSubmit = (e) => {
        // set user type based on active tab
        console.log(userData);
        e.preventDefault();
        if (activeTab === "SignUpDriver") {
            onAddDriver(userData);
        } else {
            onAddClient(userData);
        }
        setUserData({
            first_name: "",
            last_name: "",
            email: "",
            password: "",
            userType: ""
        })
    };

    return (
        <FormWrapper title={activeTab === "SignUpDriver" ? "Add Driver" : "Add Client"} onSubmit={handleSubmit}>
            <div className="tab">
                <label
                    className={`tablinks ${activeTab === 'SignUpClient' ? 'active' : ''}`}
                    onClick={() => handleTabClick('SignUpClient')}
                >
                    Sign Up as Client
                </label>
                <label
                    className={`tablinks ${activeTab === 'SignUpDriver' ? 'active' : ''}`}
                    onClick={() => handleTabClick('SignUpDriver')}
                >
                    Sign Up as Driver
                </label>
            </div>
            <TextInput
                type='text'
                label="First Name"
                name="itemName"
                value={userData.first_name}
                onChange={(e) => setUserData({...userData, first_name: e.target.value ,userType: activeTab === "SignUpDriver" ? "DRIVER" : "CLIENT"})}
            />
            <TextInput
                type='text'
                label="Last Name"
                name="itemName"
                value={userData.last_name}
                onChange={(e) => setUserData({...userData, last_name: e.target.value ,userType: activeTab === "SignUpDriver" ? "DRIVER" : "CLIENT"})}
            />
            <TextInput
                type='text'
                label="Email"
                name="itemPrice"
                value={userData.email}
                onChange={(e) => setUserData({...userData, email: e.target.value ,userType: activeTab === "SignUpDriver" ? "DRIVER" : "CLIENT"})}
            />
            <TextInput
                type='text'
                label="Password"
                name="itemPrice"
                value={userData.password}
                onChange={(e) => setUserData({...userData, password: e.target.value ,userType: activeTab === "SignUpDriver" ? "DRIVER" : "CLIENT"})}
            />
            <button type="submit">{activeTab === "SignUpDriver" ? "Add Driver" : "Add Client"}</button>
        </FormWrapper>
    );
};

export default AddUserForm;