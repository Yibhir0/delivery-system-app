import React, { useState, useEffect } from "react";

import { Container, Row, Col } from "reactstrap";

import {
  getAllPackages,
  getDeliveryRequestsByClient,
  getDriverPackages,
  getUnassignedPackages
} from '../api/get/GetDataService.js'

import PackageCard from "../components/UI/package-card/PackageCard.jsx";
import Helmet from "../components/Helmet/Helmet.jsx";
import ReactPaginate from "react-paginate";
import "../styles/pagination.css";

const Packages = ({ fetchPackagesCriteria }) => {

  const [pageNumber, setPageNumber] = useState(0);

  const [packages, setPackages] = useState([]);

  useEffect(() => {

    const savedUser = JSON.parse(localStorage.getItem("user"));
    const { id, userType } = savedUser;
    const fetchPackages = async () => {
      // TODO: refactor this to better design
      if (fetchPackagesCriteria === "Client") {
        const data = await getDeliveryRequestsByClient(id);
        setPackages(data);
      } else if (fetchPackagesCriteria === "Assigned") {
        const data = await getDriverPackages(id);
        setPackages(data);
      } else if (fetchPackagesCriteria === "Unassigned") {
        const data = await getUnassignedPackages();
        setPackages(data);
      } else if (fetchPackagesCriteria === "All") {
        const data = await getAllPackages();
        setPackages(data);
      }
    };
    fetchPackages();
  }, [fetchPackagesCriteria]);

  const searchedProduct = packages;

  const productPerPage = 4;
  const visitedPage = pageNumber * productPerPage;
  const displayPage = searchedProduct.slice(
    visitedPage,
    visitedPage + productPerPage
  );

  const pageCount = Math.ceil(searchedProduct.length / productPerPage);

  const changePage = ({ selected }) => {
    setPageNumber(selected);
  };

  return (
    <Helmet title="All Packages">
      <Container>
        <Row>
          {displayPage.map((item) => (
            <Col
              lg="3"
              md="4"
              sm="6"
              xs="6"
              key={item.id}
              className="mb-4 mt-4"
            >
              <PackageCard item={item} />
            </Col>
          ))}
          <div className="d-flex justify-content-center mt-4 mb-4">
            <ReactPaginate
              pageCount={pageCount}
              onPageChange={changePage}
              previousLabel={"Prev"}
              nextLabel={"Next"}
              containerClassName="paginationBttns"
            />
          </div>
        </Row>
      </Container>
    </Helmet>
  );
};

export default Packages;
