
import { createContext, useState, useEffect } from "react";

const UserContext = createContext({

    first_name: "",
    last_name: "",
    email: "",
    password: "",
    userType: ""

});

export const UserProvider = ({ children }) => {

    const savedUser = JSON.parse(localStorage.getItem("user"));

    const [user, setUser] = useState(savedUser || {
        id: "",
        first_name: "",
        last_name: "",
        email: "",
        password: "",
        userType: ""

    });

    useEffect(() => {

        if (user.id || user.first_name || user.last_name || user.email || user.password || user.userType) {
            localStorage.setItem("user", JSON.stringify(user));
        }
    }, [user]);

    const login = (id, first_name, last_name, email, password, userType) => {
        setUser({ id, first_name, last_name, email, password, userType });

    };

    const logout = () => {
        setUser({
            id: "",
            first_name: "",
            last_name: "",
            email: "",
            password: "",
            userType: ""
        }
        );

        localStorage.removeItem("user");
    };

    return (
        <UserContext.Provider value={{ user, login, logout }}>
            {children}
        </UserContext.Provider>
    );
};

export default UserContext;


