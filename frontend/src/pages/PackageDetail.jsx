import React from 'react';
import styled from 'styled-components';
import {useLocation, useNavigate} from 'react-router-dom';
import {deleteDeliveryRequest, removePackageFromDriver} from '../api/delete/DeleteDataService';
import {getDeliveryRequestByPackageId, getUserById} from "../api/get/GetDataService.js";
import BasicInfo from "../components/UI/UserProfile/BasicInfo.jsx";
import Contacts from "../components/UI/UserProfile/Contacts.jsx";
import Address from "../components/UI/UserProfile/Address.jsx";
import PackageDetailsForm from "../components/Forms/PackageDetailsForm.jsx";
import {addPackageToDriver} from "../api/put/PutDataService.js";

// Styled Components
const Container = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    max-width: 800px;
    margin: 20px auto;
`;

const Section = styled.div`
    width: 100%;
    margin: 20px 0;
    padding: 20px;
    background-color: #ffffff;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
`;

const SectionTitle = styled.h3`
    margin-bottom: 15px;
    color: #333;
    font-size: 1.2rem;
    border-bottom: 1px solid #ddd;
    padding-bottom: 5px;
`;

const Attribute = styled.div`
    margin: 10px 0;
    font-size: 16px;
    color: #555;
`;

const AssignButton = styled.button`
    padding: 10px 20px;
    color: white;
    background-color: #4AB84AFF;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    margin-top: 20px;
    margin-left: 10px;
`;

const CancelButton = styled.button`
    padding: 10px 20px;
    background-color: #ff4d4d;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    margin-top: 20px;

    &:hover {
        background-color: #ff1a1a;
    }
`;


// Component
const PackageDetail = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const packageId = location.state?.id;
    const [driver, setDriver] = React.useState(null);

    const [packageData, setPackageData] = React.useState({
        "id": 0,
        "client": {
            "id": 0,
            "first_name": "string",
            "last_name": "string",
            "address": {
                "number": 0,
                "street": "string",
                "city": "string",
                "country": "string",
                "zipCode": "string"
            },
            "phone_number": 0,
            "userType": "ADMIN",
            "email": "string",
            "password": "string",
            "active": true,
            "fullName": "string"
        },
        "packageDetails": {
            "id": 0,
            "tracker": {
                "id": 0,
                "status": "CREATED",
                "location": {
                    "number": 0,
                    "street": "string",
                    "city": "string",
                    "country": "string",
                    "zipCode": "string"
                }
            },
            "packageType": "FRAGILE",
            "weight": 0,
            "height": 0,
            "width": 0,
            "length": 0
        },
        "payment": {
            "id": 0,
            "paymentType": "CREDIT_CARD",
            "amount": 0,
            "paymentDate": "2024-11-28T16:22:22.318Z",
            "status": "COMPLETED"
        },
        "senderAddress": {
            "number": 0,
            "street": "string",
            "city": "string",
            "country": "string",
            "zipCode": "string"
        },
        "receiverAddress": {
            "number": 0,
            "street": "string",
            "city": "string",
            "country": "string",
            "zipCode": "string"
        },
        "cost": 0,
        "assigned": true
    });

    React.useEffect(() => {
        const fetchData = async () => {
            if (packageId !== 0) {
                const data = await getDeliveryRequestByPackageId(packageId);
                const savedUser = JSON.parse(localStorage.getItem("user"));
                const {id} = savedUser;
                const driverData = await getUserById(id);
                if (driverData.userType === 'DRIVER') {
                    setDriver(driverData);
                }
                setPackageData(data);
            }
        };
        fetchData();
    }, [packageId]);

    console.log('driverData:', driver);

    const onCancel = async () => {
        await deleteDeliveryRequest(packageData.id);
        alert('Package Request Cancelled');
        navigate('/packages');
    };

    const UnAssignPackageFromDriver = () => {
        const unassign = async () => {
            await removePackageFromDriver(driver.id, packageId);
            alert('Package Unassigned from Driver');
            setPackageData({...packageData, assigned: false});
        }
        unassign();
    }

    const AssignPackageToDriver = () => {
        const assign = async () => {
            await addPackageToDriver(driver.id, packageId, {});
            alert('Package Assigned to Driver');
            setPackageData({...packageData, assigned: true});
        }
        assign();
    }




    return (
        <Container>
            <h2>Package Details</h2>

            <div className="row">
                <div className="col">
                    {/* Payment Receipt Section */}
                    <Section>
                        <SectionTitle>Payment Receipt</SectionTitle>
                        <Attribute><strong>Cost:</strong> {packageData.cost}$</Attribute>
                        <Attribute><strong>Payment Method:</strong> {packageData.payment.paymentType}</Attribute>
                        <Attribute><strong>Transaction Date:</strong> {packageData.payment.paymentDate}</Attribute>
                        <Attribute><strong>Status:</strong> {packageData.payment.status}</Attribute>
                    </Section>
                    {/* Delivery Details Section */}
                    <Section>
                        <SectionTitle>Delivery Details</SectionTitle>
                        <Attribute><strong>Sender Address:</strong>
                            {packageData.senderAddress.number},
                            {packageData.senderAddress.street},
                            {packageData.senderAddress.city},
                            {packageData.senderAddress.country},
                            {packageData.senderAddress.zipCode}
                        </Attribute>
                        <Attribute><strong>Sender Address:</strong>
                            {packageData.receiverAddress.number},
                            {packageData.receiverAddress.street},
                            {packageData.receiverAddress.city},
                            {packageData.receiverAddress.country},
                            {packageData.receiverAddress.zipCode}
                        </Attribute>
                        {driver && (
                            packageData.assigned ? (
                                <CancelButton onClick={UnAssignPackageFromDriver}>Unassign from Self</CancelButton>
                            ) : (
                                <AssignButton onClick={AssignPackageToDriver}>Assign to Self</AssignButton>
                            )
                        )}

                    </Section>
                </div>
                <div className="col">
                    <PackageDetailsForm packageId={packageId} userType={driver ? 'DRIVER' : 'CLIENT'}/>
                </div>
            </div>
            <div className="row" style={{alignItems: "stretch"}}>
                <div className="col">
                </div>
            </div>

            {/* Cancel Button */}
            <CancelButton onClick={onCancel}>🗑 Cancel Package Request</CancelButton>
        </Container>
    );
};

export default PackageDetail;
