import React, { useEffect, useState } from "react";
import { getAllWarehouses, getAllDrivers, getAllClients, getAllPackages } from "../api/get/GetDataService.js";
import "../styles/admin-dashboard.css"; // Import the CSS file


import { useNavigate } from "react-router-dom";
const AdminDashboard = () => {
    const [warehouses, setWarehouses] = useState([]);
    const [drivers, setDrivers] = useState([]);
    const [clients, setClients] = useState([]);
    const [packages, setPackages] = useState([]);
    const [activeTab, setActiveTab] = useState("warehouses");

    const navigate = useNavigate();
    // Fetch data based on the active tab
    useEffect(() => {
        const fetchData = async () => {
            switch (activeTab) {
                case "warehouses":
                    const warehouseData = await getAllWarehouses();
                    setWarehouses(warehouseData);
                    break;
                case "drivers":
                    const driverData = await getAllDrivers();
                    setDrivers(driverData);
                    break;
                case "clients":
                    const clientData = await getAllClients();
                    setClients(clientData);
                    break;
                case "packages":
                    const packageData = await getAllPackages();
                    setPackages(packageData);
                    break;
                default:
                    break;
            }
        };
        fetchData();
    }, [activeTab]);


    const addWarehouse = () => {
        navigate("/add-warehouse");
    };
    return (
        <div className="admin-dashboard">
            <div className="tabs">
                <button onClick={() => setActiveTab("warehouses")} className={activeTab === "warehouses" ? "active" : ""}>Warehouses</button>
                <button onClick={() => setActiveTab("drivers")} className={activeTab === "drivers" ? "active" : ""}>Drivers</button>
                <button onClick={() => setActiveTab("clients")} className={activeTab === "clients" ? "active" : ""}>Clients</button>
                <button onClick={() => setActiveTab("packages")} className={activeTab === "packages" ? "active" : ""}>Packages</button>
            </div>




            <div className="content">
                {/* Warehouse Section */}
                {activeTab === "warehouses" && (
                    <div className="warehouse-list">
                        <h2>Warehouse List</h2>
                        <button className="add-btn" onClick={addWarehouse}>
                            + Add Warehouse
                        </button>
                        <br />
                        <hr />
                        <br />
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Address</th>
                                </tr>
                            </thead>
                            <tbody>
                                {warehouses.map((warehouse) => (
                                    <tr key={warehouse.id}>
                                        <td>{warehouse.id}</td>
                                        <td>{`${warehouse.address.number}, ${warehouse.address.street}, ${warehouse.address.city}, ${warehouse.address.country}, ${warehouse.address.zipCode}`}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}

                {/* Driver Section */}
                {activeTab === "drivers" && (
                    <div className="driver-list">
                        <h2>Driver List</h2>
                        {/* <button className="add-btn" onClick={() => alert("TODO: add driver form")}>
                            + Add Driver
                        </button> */}
                        <br />
                        <hr />
                        <br />
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Email</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                {drivers.map((driver) => (
                                    <tr key={driver.id}>
                                        <td>{driver.id}</td>
                                        <td>{driver.first_name}</td>
                                        <td>{driver.last_name}</td>
                                        <td>{driver.email}</td>
                                        <td>{driver.active ? "Active" : "InActive"}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}

                {/* Client Section */}
                {activeTab === "clients" && (
                    <div className="client-list">
                        <h2>Client List</h2>
                        {/* <button className="add-btn" onClick={() => alert("TODO: add client form")}>
                            + Add Client
                        </button> */}
                        <br />
                        <hr />
                        <br />
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Email</th>
                                </tr>
                            </thead>
                            <tbody>
                                {clients.map((client) => (
                                    <tr key={client.id}>
                                        <td>{client.id}</td>
                                        <td>{client.first_name}</td>
                                        <td>{client.last_name}</td>
                                        <td>{client.email}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}

                {/* Package Section */}
                {activeTab === "packages" && (
                    <div className="package-list">
                        <h2>Package List</h2>
                        {/* <button className="add-btn" onClick={() => alert("TODO: add package form")}>
                            + Add Package
                        </button> */}
                        <br />
                        <hr />
                        <br />
                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Package Type</th>
                                    <th>Dimensions</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                {packages.map((pkg) => (
                                    <tr key={pkg.id}>
                                        <td>{pkg.id}</td>
                                        <td>{pkg.packageType}</td>
                                        <td>Weigth:{pkg.weight} Height: {pkg.height} Width: {pkg.width} Length: {pkg.length}</td>
                                        <td>{pkg.tracker.status}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                )}
            </div>
        </div>
    );
};

export default AdminDashboard;
