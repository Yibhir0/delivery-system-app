import { putDataToEndpoint } from '../apiClient';

// Company PUT Endpoints / Admin PUT Endpoints

// PUT /api/warehouse/{warehouseId}/{packageId} - Add a package to a warehouse
export const addPackageToWarehouse = async (warehouseId, packageId, data) => {
    return await putDataToEndpoint(`/api/warehouse/${warehouseId}/${packageId}`, data);
};

// PUT /api/warehouse/{warehouseId}/address - Update a warehouse address
export const updateWarehouseAddress = async (warehouseId, data) => {
    return await putDataToEndpoint(`/api/warehouse/${warehouseId}/address`, data);
};

// PUT /api/user/{userId}/activate - Activate a user account
export const activateUserAccount = async (userId, data) => {
    return await putDataToEndpoint(`/api/user/${userId}/activate`, data);
};

// PUT /api/tracker/{trackerId}/status - Update a tracker
export const updateTrackerStatus = async (trackerId, data) => {
    return await putDataToEndpoint(`/api/tracker/${trackerId}/status`, data);
};

// PUT /api/payment/{paymentId} - Update a payment
export const updatePayment = async (paymentId, data) => {
    return await putDataToEndpoint(`/api/payment/${paymentId}`, data);
};

// PUT /api/driver/{driverId}/{packageId} - Add a package to a driver
export const addPackageToDriver = async (driverId, packageId) => {
    return await putDataToEndpoint(`/api/driver/${driverId}/${packageId}`);
};

// PUT /api/company/name - Update the company's name
export const updateCompanyName = async (data) => {
    return await putDataToEndpoint('/api/company/name', data);
};

// PUT /api/company/address - Update the company's address
export const updateCompanyAddress = async (data) => {
    return await putDataToEndpoint('/api/company/address', data);
};

// Driver PUT Endpoints

// PUT /api/warehouse/{warehouseId}/{packageId} - Add a package to a warehouse
export const addPackageToWarehouseByDriver = async (warehouseId, packageId, data) => {
    return await putDataToEndpoint(`/api/warehouse/${warehouseId}/${packageId}`, data);
};

// PUT /api/tracker/{trackerId}/status - Update a tracker
export const updateTrackerStatusByDriver = async (trackerId, data) => {
    return await putDataToEndpoint(`/api/tracker/${trackerId}/status`, data);
};

// PUT /api/driver/{driverId}/{packageId} - Add a package to a driver
export const addPackageToDriverByDriver = async (driverId, packageId, data) => {
    return await putDataToEndpoint(`/api/driver/${driverId}/${packageId}`, data);
};

// User PUT Endpoints

// POST /api/user/{userId}
export const updateUser = async (userId, data) => {
    return await putDataToEndpoint(`/api/user/${userId}`, data);
};

// // PUT /api/user/{userId}/phoneNumber - Update a user's phone number
// export const updateUserPhoneNumber = async (userId, data) => {
//     return await putDataToEndpoint(`/api/user/${userId}/phoneNumber`, data);
// };
//
// // PUT /api/user/{userId}/password - Update a user's password
// export const updateUserPassword = async (userId, data) => {
//     return await putDataToEndpoint(`/api/user/${userId}/password`, data);
// };
//
// // PUT /api/user/{userId}/lastName - Update a user's last name
// export const updateUserLastName = async (userId, data) => {
//     return await putDataToEndpoint(`/api/user/${userId}/lastName`, data);
// };
//
// // PUT /api/user/{userId}/firstName - Update a user's first name
// export const updateUserFirstName = async (userId, data) => {
//     return await putDataToEndpoint(`/api/user/${userId}/firstName`, data);
// };
//
// // PUT /api/user/{userId}/email - Update a user's email
// export const updateUserEmail = async (userId, data) => {
//     return await putDataToEndpoint(`/api/user/${userId}/email`, data);
// };
//
// // PUT /api/user/{userId}/address - Update a user's address
// export const updateUserAddress = async (userId, data) => {
//     return await putDataToEndpoint(`/api/user/${userId}/address`, data);
// };

// PUT /api/package/{packageId} - Update package dimensions
export const updatePackageDimensions = async (packageId, data) => {
    return await putDataToEndpoint(`/api/package/${packageId}`, data);
};

// // PUT /api/package/{packageId}/weight - Update package weight
// export const updatePackageWeight = async (packageId, data) => {
//     return await putDataToEndpoint(`/api/package/${packageId}/weight`, data);
// };
//
// // PUT /api/package/{packageId}/length - Update package length
// export const updatePackageLength = async (packageId, data) => {
//     return await putDataToEndpoint(`/api/package/${packageId}/length`, data);
// };
//
// // PUT /api/package/{packageId}/height - Update package height
// export const updatePackageHeight = async (packageId, data) => {
//     return await putDataToEndpoint(`/api/package/${packageId}/height`, data);
// };

// PUT /api/deliveryRequest/{deliveryRequestId}/senderAddress - Update sender address
export const updateSenderAddress = async (deliveryRequestId, data) => {
    return await putDataToEndpoint(`/api/deliveryRequest/${deliveryRequestId}/senderAddress`, data);
};

// PUT /api/deliveryRequest/{deliveryRequestId}/receiverAddress - Update receiver address
export const updateReceiverAddress = async (deliveryRequestId, data) => {
    return await putDataToEndpoint(`/api/deliveryRequest/${deliveryRequestId}/receiverAddress`, data);
};
