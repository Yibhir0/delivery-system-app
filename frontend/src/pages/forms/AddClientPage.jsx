// import React, { useContext } from 'react';
//
// import AddUserForm from '../../components/Forms/AddUserForm.jsx';
// import Helmet from "../../components/Helmet/Helmet.jsx";
//
// import UserContext from '../../contexts/userContext';
//
// import { signUpUser } from '../../api/post/PostDataService.js';
//
// // import { useNavigate } from 'react-router-dom';
//
// import useAppNavigator from '../../hooks/useAppNavigator.jsx';
//
// const AddClientPage = () => {
//
//     // const navigate = useNavigate();
//     const { login } = useContext(UserContext);
//
//     const navigateTo = useAppNavigator();
//
//     const onSignup = async (p) => {
//         try {
//             const data = await signUpUser(p);
//             const { id, first_name, last_name, email, password, userType } = data;
//
//
//
//             login(id, first_name, last_name, email, password, userType);
//             alert('Client Added Successfully and Logged In');
//             navigateTo(userType);
//         } catch (error) {
//             console.error('Error :', error);
//         }
//     }
//
//     return (
//         <Helmet title="Signup">
//             <AddUserForm onAddUser={onSignup} userType={"CLIENT"} />
//         </Helmet>
//     );
// }
//
// export default AddClientPage;
//
