import React, {useEffect, useState} from "react";
import BasicInfo from "../components/UI/UserProfile/BasicInfo";
import Contacts from "../components/UI/UserProfile/Contacts.jsx";
import Address from "../components/UI/UserProfile/Address";
import "../styles/user-profile.css";
import {getUserById} from "../api/get/GetDataService.js";
import {updateUser} from "../api/put/PutDataService.js";

const UserProfile = () => {

    const [change, setChange] = useState(false);
    const [userProfile, setUserProfile] = useState({
        id: 0,
        first_name: "string",
        last_name: "string",
        address: {
            number: 0,
            street: "string",
            city: "string",
            country: "string",
            zipCode: "string"
        },
        phone_number: 0,
        userType: "CLIENT",
        email: "string",
        password: "string",
        active: true,
        fullName: "string"
    });

    const [tempUserProfile, setTempUserProfile] = useState({
        id: 0,
        first_name: "string",
        last_name: "string",
        address: {
            number: 0,
            street: "string",
            city: "string",
            country: "string",
            zipCode: "string"
        },
        phone_number: 0,
        userType: "CLIENT",
        email: "string",
        password: "string",
        active: true,
        fullName: "string"
    });

    const handleChange = (name, value) => {
        setChange(true);
        console.log("Change happened: "+name+" "+value);
        setTempUserProfile((prev) => ({ ...prev, [name]: value }));
    };


    useEffect(() => {

        const savedUser = JSON.parse(localStorage.getItem("user"));
        const { id } = savedUser;
        const fetchUserProfile = async () => {
            const data = await getUserById(id);
            setUserProfile(data);
            setTempUserProfile(data);
        };
        fetchUserProfile();
    }, []);

    function cancelChange() {
        console.log("Cancel change");
        setChange(false);
        setTempUserProfile(userProfile);
    }

    function saveChange() {
        const savedUser = JSON.parse(localStorage.getItem("user"));
        const { id } = savedUser;

        const saveProfile = async () => {
            const data = await updateUser(id, tempUserProfile);
            setUserProfile(data);
            setTempUserProfile(data);
            // update local storage
            localStorage.setItem("user", JSON.stringify(data));
        };
        saveProfile().then(r => alert("Profile Saved!"));
        setChange(false);
    }

    return (
        <div className="user-profile">
            <div className="profile-header">
                <h1>User Profile</h1>
            </div>
            <div className="profile-row">
                <div className="profile-col">
                    <BasicInfo user={tempUserProfile} setUser={handleChange}/>
                </div>
            </div>
            <div className="profile-row" style={{ alignItems: "stretch" }}>
                <div className="profile-col">
                    <Contacts user={tempUserProfile} setUser={handleChange}/>
                </div>
                <div className="profile-col">
                    <Address user={tempUserProfile} setUser={handleChange}/>
                </div>
            </div>
            <div className="profile-row">
                <div className="profile-col">
                    <div style={{float: "right"}}>
                        <button className="cancel" disabled={!change} onClick={cancelChange}>Cancel</button>
                        <button className="save" disabled={!change} onClick={saveChange}>Save</button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default UserProfile;
