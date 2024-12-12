import { deleteDataFromEndpoint } from '../apiClient';

// Driver DELETE Endpoints

// DELETE /api/warehouse/{warehouseId}/{packageId}
export const removePackageFromWarehouse = async (warehouseId, packageId) => {
    return await deleteDataFromEndpoint(`/api/warehouse/${warehouseId}/${packageId}`);
};

// DELETE /api/driver/{driverId}/{packageId}
export const removePackageFromDriver = async (driverId, packageId) => {
    return await deleteDataFromEndpoint(`/api/driver/${driverId}/${packageId}`);
};

// Company DELETE Endpoints / Admin DELETE Endpoints

// DELETE /api/warehouse/{warehouseId}/{packageId}
export const adminRemovePackageFromWarehouse = async (warehouseId, packageId) => {
    return await deleteDataFromEndpoint(`/api/warehouse/${warehouseId}/${packageId}`);
};

// DELETE /api/driver/{driverId}/{packageId}
export const adminRemovePackageFromDriver = async (driverId, packageId) => {
    return await deleteDataFromEndpoint(`/api/driver/${driverId}/${packageId}`);
};

// DELETE /api/user/{userId}
export const deleteUser = async (userId) => {
    return await deleteDataFromEndpoint(`/api/user/${userId}`);
};

// DELETE /api/deliveryRequest/{deliveryRequestId}
export const deleteDeliveryRequest = async (deliveryRequestId) => {
    return await deleteDataFromEndpoint(`/api/deliveryRequest/${deliveryRequestId}`);
};

// DELETE /api/company/warehouse/{warehouseId}
export const removeWarehouseFromCompany = async (warehouseId) => {
    return await deleteDataFromEndpoint(`/api/company/warehouse/${warehouseId}`);
};


