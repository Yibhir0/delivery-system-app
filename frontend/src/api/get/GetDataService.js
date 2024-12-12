import { fetchDataByEndpoint } from '../apiClient';

// General GET Endpoints
export const ping = async () => {
    return await fetchDataByEndpoint('/ping');
};

export const getDotenv = async () => {
    return await fetchDataByEndpoint('/dotenv');
};

export const getRoot = async () => {
    return await fetchDataByEndpoint('/');
};

// Company/Admin GET Endpoints
export const getPaymentById = async (paymentId) => {
    return await fetchDataByEndpoint(`/api/payment/${paymentId}`);
};

export const getAllDeliveryRequests = async () => {
    return await fetchDataByEndpoint('/api/deliveryRequest/');
};

export const getWarehouseById = async (warehouseId) => {
    return await fetchDataByEndpoint(`/api/warehouse/${warehouseId}`);
};

export const getAllWarehouses = async () => {
    return await fetchDataByEndpoint('/api/warehouse/');
};

export const getUserById = async (userId) => {
    return await fetchDataByEndpoint(`/api/user/${userId}`);
};

export const getAllUsers = async () => {
    return await fetchDataByEndpoint('/api/user/');
};

export const getAllClients = async () => {
    return await fetchDataByEndpoint('/api/user/clients');
};

export const getTrackerById = async (trackerId) => {
    return await fetchDataByEndpoint(`/api/tracker/${trackerId}`);
};

export const getAllTrackers = async () => {
    return await fetchDataByEndpoint('/api/tracker/');
};

export const getAllPayments = async () => {
    return await fetchDataByEndpoint('/api/payment/');
};

export const getPackageById = async (packageId) => {
    return await fetchDataByEndpoint(`/api/package/${packageId}`);
};

export const getDeliveryRequestByPackageId = async (packageId) => {
    return await fetchDataByEndpoint(`/api/package/${packageId}/deliveryRequest`);
};

export const getAllPackages = async () => {
    return await fetchDataByEndpoint('/api/package/');
};

export const getNotificationById = async (notificationId) => {
    return await fetchDataByEndpoint(`/api/notification/${notificationId}`);
};

export const getAllNotifications = async () => {
    return await fetchDataByEndpoint('/api/notification/');
};

export const getDriverById = async (driverId) => {
    return await fetchDataByEndpoint(`/api/driver/${driverId}`);
};

export const getDriverPackages = async (driverId) => {
    return await fetchDataByEndpoint(`/api/driver/${driverId}/packages`);
};

export const getAllDrivers = async () => {
    return await fetchDataByEndpoint('/api/driver/');
};

export const getDeliveryRequestById = async (deliveryRequestId) => {
    return await fetchDataByEndpoint(`/api/deliveryRequest/${deliveryRequestId}`);
};

export const getUnassignedPackages = async () => {
    return await fetchDataByEndpoint(`/api/deliveryRequest/unassigned`);
};

export const getCompanyInfo = async () => {
    return await fetchDataByEndpoint('/api/company/');
};

// User GET Endpoints (Adjusted for 10 endpoints)
export const getNotificationsByUserId = async (userId) => {
    return await fetchDataByEndpoint(`/api/notification/user/${userId}`);
};

export const getUnreadNotificationCountByUserId = async (userId) => {
    return await fetchDataByEndpoint(`/api/notification/user/${userId}/unread`);
};

export const getDeliveryRequestsByClient = async (userId) => {
    return await fetchDataByEndpoint(`/api/deliveryRequest/client/${userId}`);
};

export const getUserDeliveryRequests = async (userId) => {
    return await fetchDataByEndpoint(`/api/deliveryRequest/user/${userId}`);
};

export const getUserPackages = async (userId) => {
    return await fetchDataByEndpoint(`/api/package/user/${userId}`);
};

export const getUserTrackers = async (userId) => {
    return await fetchDataByEndpoint(`/api/tracker/user/${userId}`);
};

export const getUserPayments = async (userId) => {
    return await fetchDataByEndpoint(`/api/payment/user/${userId}`);
};

export const getUserNotifications = async (userId) => {
    return await fetchDataByEndpoint(`/api/notification/user/${userId}`);
};

export const getUserDrivers = async (userId) => {
    return await fetchDataByEndpoint(`/api/driver/user/${userId}`);
};

export const getUserWarehouses = async (userId) => {
    return await fetchDataByEndpoint(`/api/warehouse/user/${userId}`);
};

// Driver GET Endpoints
export const getDriverTrackerById = async (trackerId) => {
    return await fetchDataByEndpoint(`/api/tracker/${trackerId}`);
};

export const getDriverPackageById = async (packageId) => {
    return await fetchDataByEndpoint(`/api/package/${packageId}`);
};

export const getDriverDeliveryRequestByPackageId = async (packageId) => {
    return await fetchDataByEndpoint(`/api/package/${packageId}/deliveryRequest`);
};

export const getDriverDeliveryRequestById = async (deliveryRequestId) => {
    return await fetchDataByEndpoint(`/api/deliveryRequest/${deliveryRequestId}`);
};
