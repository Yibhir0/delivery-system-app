import { useNavigate } from 'react-router-dom';

const useAppNavigator = () => {

    const navigate = useNavigate();

    // Custom method to navigate based on a parameter
    const navigateTo = (userType) => {
        if (userType === "ADMIN") {
            navigate('/admin');
        }
        else if (userType === "CLIENT") {
            navigate('/client');
        }
        else if (userType === "DRIVER") {
            navigate('/driver');
        }
        else {
            navigate('/public');
        }
    };

    return navigateTo;
};

export default useAppNavigator; 
