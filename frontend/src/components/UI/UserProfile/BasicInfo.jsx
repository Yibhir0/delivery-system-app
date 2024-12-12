import React from "react";

const BasicInfo = ({ user, setUser }) => {
    return (
        <div className="section basic-info">
            <h2>Basic Info</h2>
            <div className="profile-row">
                <div className="profile-col">
                    <label>First Name</label>
                    <input
                        type="text"
                        name="first_name"
                        value={user.first_name}
                        onChange={(e)=>{setUser(e.target.name, e.target.value)}}
                    />
                </div>
                <div className="profile-col">
                    <label>Last Name</label>
                    <input
                        type="text"
                        name="last_name"
                        value={user.last_name}
                        onChange={(e)=>{setUser(e.target.name, e.target.value)}}
                    />
                </div>
            </div>
            {/* add change password dialog */}
        </div>
    );
};

export default BasicInfo;
