import React from "react";

import "../../../styles/product-card.css";

import packageImages from "../../../assets/images/packages/packageImages"
import { Link } from "react-router-dom";

import { useNavigate } from "react-router-dom";

const PackageCard = ({ item }) => {

  const navigate = useNavigate();

  const seeMore = () => {
    const id = item.id;
    navigate('/package-detail', { state: { id } });
  };



  const { tracker, id, weight, height, width ,packageType} = item?.packageDetails;

  const image01 = packageImages[packageType];


  return (
    <div className="product__item d-flex flex-column justify-content-between">
      <div className="product__content">
        <img className="product__img w-50" src={image01} alt="Pizza" />
        <h5>
          <Link to={`/packages/${id}`}>
            <strong>ID:</strong> {id}
            <br/>
            <strong>Size:</strong> {width} x {height} x {weight}
          </Link>
        </h5>
      </div>
      <div className="d-flex flex-column align-items-center justify-content-between">
        <span className="product__price mb-2">{tracker?.status}  </span>
        <button className="addTOCART__btn" onClick={seeMore}>
          See more
        </button>
      </div>
    </div>
  );
};

export default PackageCard;
