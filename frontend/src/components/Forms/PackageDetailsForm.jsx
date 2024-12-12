import React, {useEffect} from "react";
import styled from "styled-components";
import {updatePackageDimensions, updateTrackerStatusByDriver, updateUser} from "../../api/put/PutDataService.js";
import packageDetail from "../../pages/PackageDetail.jsx";
import {getPackageById} from "../../api/get/GetDataService.js";

// Styled Components
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


const ModifiableAttribute = styled.input.attrs({type: 'text'})`
    margin: 10px 0;
    font-size: 16px;
    color: #555;
    width: 50%;
`;

const EditButton = styled.button`
    padding: 10px 20px;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    margin-top: 20px;
    margin-left: 10px;
`;

const CancelButton = styled.button`
    padding: 10px 20px;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    margin-top: 20px;
`;


const PackageDetailsForm = ({ packageId ,userType}) => {

    const [change, setChange] = React.useState(false);
    const [cancel, setCancel] = React.useState(0);
    const [packageData, setPackageData] = React.useState({
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
    });

    useEffect(() => {
        const fetchPackage = async () => {
            const data = await getPackageById(packageId);
            setPackageData(data);
            setChange(false);
        };
        fetchPackage();
    }, [cancel]);

    const cancelChanges = () => {
        setCancel(cancel + 1);
    }

    const saveChanges = () => {
        const tracker = packageData.tracker;
        const data = {
            ...packageData,
            "weight": parseInt(packageData.weight),
            "height": parseInt(packageData.height),
            "width": parseInt(packageData.width),
            "length": parseInt(packageData.length),
        }
        const updatePackage = async (id,pkg) => {
            await updatePackageDimensions(id, pkg);
            await updateTrackerStatusByDriver(tracker.id, {status: tracker.status, location: tracker.location});
        };
        console.log("Data to be saved: ", data);
        updatePackage(packageId,data).then(r => {
            alert("Changes saved successfully!");
            setChange(false);
        })
    }

    const setDimension = (name, value)  => {
        setPackageData({...packageData, [name]: value});
        console.log("Package details: ", packageData);
        setChange(true);
    }

    const setTrackerStatus = (name, value) => {
        const tracking = packageData.tracker;
        setPackageData({...packageData, tracker: {...tracking, [name]: value}});
        console.log("Package details: ", packageData);
        setChange(true);
    }



    return(
        <Section>
            <SectionTitle>Package Details</SectionTitle>
            <Attribute><strong>Id: </strong> {packageData.id}</Attribute>
                {userType === 'CLIENT' ? (
                    <Attribute><strong>Status:</strong> {packageData.tracker.status}</Attribute>
                ) : (
                    <Attribute><strong>Status:</strong>
                        <select name="status" value={packageData.tracker.status} onChange={(e) => setTrackerStatus(e.target.name, e.target.value)}>
                        <option value="CREATED">CREATED</option>
                        <option value="TO_BE_PICKED">TO_BE_PICKED</option>
                        <option value="IN_TRANSIT">IN_TRANSIT</option>
                        <option value="PENDING">PENDING</option>
                        <option value="READY_FOR_DELIVERY">READY_FOR_DELIVERY</option>
                        <option value="OUT_FOR_DELIVERY">OUT_FOR_DELIVERY</option>
                        <option value="PICKED_UP">PICKED_UP</option>
                        <option value="DELIVERED">DELIVERED</option>
                        </select>
                    </Attribute>
                )}
            <Attribute className="col"><strong>Height:</strong>
                <ModifiableAttribute name="height"
                                     value={packageData.height}
                                     onChange={(e) => {
                                         setDimension(e.target.name, e.target.value)
                                     }}/> cm</Attribute>
            <Attribute><strong>Width:</strong>
                <ModifiableAttribute name="width"
                                     value={packageData.width}
                                     onChange={(e) => {
                                         setDimension(e.target.name, e.target.value)
                                     }}/> cm</Attribute>
            <Attribute><strong>Length:</strong>
                <ModifiableAttribute name="length"
                                     value={packageData.length}
                                     onChange={(e) => {
                                         setDimension(e.target.name, e.target.value)
                                     }}/> cm</Attribute>
            <Attribute><strong>Weight:</strong>
                <ModifiableAttribute name="weight"
                                     value={packageData.weight}
                                     onChange={(e) => {
                                         setDimension(e.target.name, e.target.value)
                                     }}/> kg</Attribute>
            <div className="row">
                <div className="col">
                    <CancelButton onClick={cancelChanges} disabled={!change} style={{ cursor: change ? 'pointer' : 'not-allowed', backgroundColor: change ? '#ff4d4d' : '#9e5252' }}>🗑 Cancel</CancelButton>
                </div>
                <div className="col">
                    <EditButton onClick={saveChanges} disabled={!change} style={{ cursor: change ? 'pointer' : 'not-allowed', backgroundColor: change ? '#4ab84a' : '#4f8750' }}>Save
                        💾</EditButton>
                </div>
            </div>
        </Section>
    );
}

export default PackageDetailsForm;