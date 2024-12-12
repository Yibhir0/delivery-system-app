import React from "react";

const Contacts = ({ user, setUser }) => {
    function changeStatus() {
        if (window.confirm(`Are you sure you want to ${user.active ? "deactivate" : "activate"} this user?`)) {
            // api call based on user.active
            if (user.active) {
                // deactivate
                console.log("Deactivate user");
            }else {
                // activate
                console.log("Activate user");
            }
        }
    }

    return (
        <div className="section contacts">
            <h2>Contact Info</h2>
            <div className="profile-row">
                <div className="profile-col">
                    <label>Phone Number</label>
                    <input
                        type="text"
                        name="phone_number"
                        value={user.phone_number}
                        onChange={(e) => {
                            // Validate: Only allow digits and ensure the length is between 10 and 13
                            const val = e.target.value;
                            if (/^\d{0,10}$/.test(val)) {
                                    setUser(e.target.name, val);
                            }else{
                                e.target.value = user.phone_number
                            }
                        }}
                    />
                </div>
                <div className="profile-col">
                    <label>Email</label>
                    <input
                        type="email"
                        name="email"
                        value={user.email}
                        onChange={(e)=>{setUser(e.target.name, e.target.value)}}
                    />
                </div>
            </div>
            <br/>
            <hr/>
            <br/>
            <div className="profile-row">
                <div className="profile-col">
                    <label>Status: </label>
                    <label style={{color: user.active ? "green" : "red"}}>{user.active ? "Active" : "Inactive"}</label>
                    {user.userType === "CLIENT" && (
                        <button className={user.active ? "deactivate" : "activate"} onClick={changeStatus}>
                            {user.active ? "Deactivate" : "Activate"}
                        </button>
                    )}                </div>
            </div>
        </div>
    );
};

export default Contacts;
