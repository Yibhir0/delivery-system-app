import React from "react";

const Address = ({ user, setUser }) => {
    return (
        <div className="section address">
            <h2>Address</h2>
            <div className="profile-row">
                <div className="profile-col">
                    <label>Number</label>
                    <input
                        type="text"
                        name="address.number"
                        value={user.address.number}
                        onChange={(e)=>{
                            // Validate: Only allow digits and ensure the length is between 10 and 13
                            const val = e.target.value;
                            if (/^\d{0,10}$/.test(val)) {
                                setUser(e.target.name, val);
                            }else{
                                e.target.value = user.address.number;
                            }
                        }}
                    />
                </div>
                <div className="profile-col">
                    <label>Street</label>
                    <input
                        type="text"
                        name="address.street"
                        value={user.address.street}
                        onChange={(e)=>{setUser(e.target.name, e.target.value)}}
                    />
                </div>
            </div>
            <div className="profile-row">
                <div className="profile-col">
                    <label>City</label>
                    <input
                        type="text"
                        name="address.city"
                        value={user.address.city}
                        onChange={(e)=>{setUser(e.target.name, e.target.value)}}
                    />
                </div>
                <div className="profile-col">
                    <label>Country</label>
                    <input
                        type="text"
                        name="address.country"
                        value={user.address.country}
                        onChange={(e)=>{setUser(e.target.name, e.target.value)}}
                    />
                </div>
            </div>
            <div className="profile-row">
                <div className="profile-col">
                    <label>Zipcode</label>
                    <input
                        type="text"
                        name="address.zipcode"
                        value={user.address.zipCode}
                        onChange={(e)=>{setUser(e.target.name, e.target.value)}}
                    />
                </div>
            </div>
        </div>
    );
};

export default Address;
