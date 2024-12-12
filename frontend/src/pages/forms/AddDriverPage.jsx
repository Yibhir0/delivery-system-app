// import React, { useContext } from 'react';
//
// import AddUserForm from '../../components/Forms/AddUserForm.jsx';
//
// import Helmet from "../../components/Helmet/Helmet.jsx";
//
// import UserContext from '../../contexts/userContext';
//
// import { signUpDriver } from '../../api/post/PostDataService.js';
//
// import useAppNavigator from '../../hooks/useAppNavigator.jsx';
//
// // import { useNavigate } from 'react-router-dom';
//
// const AddDriverPage = () => {
//
//     const navigateTo = useAppNavigator();
//
//     const { login } = useContext(UserContext);
//
//
//     // const navigate = useNavigate();
//     const onAddDriver = async (p) => {
//         try {
//             const data = await signUpDriver(p);
//             const { id, first_name, last_name, email, password, userType } = data;
//             login(id, first_name, last_name, email, password, userType);
//             alert('Driver Added Successfully and Logged In');
//             navigateTo(userType);
//         } catch (error) {
//             console.error('Error :', error);
//         }
//     }
//
//     return (
//         <Helmet title="Add Driver">
//             <AddUserForm onAddUser={onAddDriver} userType={"DRIVER"} />
//         </Helmet>
//     );
// }
//
// export default AddDriverPage;
//
//
