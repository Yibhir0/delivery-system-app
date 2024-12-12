import { postDataToEndpoint } from '../apiClient';

// POST /api/user/signup
export const signUpUser = async (data) => {
    return await postDataToEndpoint('/api/user/signup', data);
};

// POST /api/user/login
export const logIn = async (data) => {
    return await postDataToEndpoint('/api/user/login', data);
};

// POST /api/payment/paypal
export const payWithPaypal = async (data) => {
    return await postDataToEndpoint('/api/payment/paypal', data);
};

// POST /api/payment/creditcard
export const payWithCreditCard = async (data) => {
    return await postDataToEndpoint('/api/payment/creditcard', data);
};

// POST /api/deliveryRequest/
export const createDeliveryRequest = async (data) => {
    return await postDataToEndpoint('/api/deliveryRequest/', data);
};

// POST /api/chatbot/ask
export const askChatBot = async (data) => {
    return await postDataToEndpoint('/api/chatbot/ask', data);
};

// POST /api/driver/signup
export const signUpDriver = async (data) => {
    return await postDataToEndpoint('/api/driver/signup', data);
};

// POST /api/company/warehouse
export const addWarehouseToCompany = async (data) => {
    console.log(data);
    return await postDataToEndpoint('/api/company/warehouse', data);
};

// POST /api/user/login (for drivers)
export const logInDriver = async (data) => {
    return await postDataToEndpoint('/api/user/login', data);
};

// POST /api/user/signup (for user registration)
export const signUpUserForCompany = async (data) => {
    return await postDataToEndpoint('/api/user/signup', data);
};

// POST /api/notification/{notificationId}/read - Mark a notification as read
export const markNotificationAsRead = async (notificationId) => {
    return await postDataToEndpoint(`/api/notification/${notificationId}/read`);
};

// POST /api/notification/{notificationId}/unread - Mark a notification as unread
export const markNotificationAsUnRead = async (notificationId) => {
    return await postDataToEndpoint(`/api/notification/${notificationId}/unread`);
};

