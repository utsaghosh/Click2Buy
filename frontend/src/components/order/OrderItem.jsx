import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function OrderItem({ orderItem, cancelButton }) {
  const {
    orderItemId,
    orderPrice,
    itemCount,
    creationDate,
    itemId,
    itemName,
    itemImage,
    userId,
    address,
  } = orderItem;

  const navigate = useNavigate();

  return (
    <div className="d-flex flex-row justify-content-center">
      <div
        className="container d-flex flex-row border border-2 rounded-2 m-1 p-1 position-relative"
        style={{ width: "90%", height: "10%" }}
      >
        <div className="image">
          <img
            src={itemImage}
            alt="item poster"
            style={{ width: "200px", height: "200px" }}
          ></img>
        </div>

        <div className="ms-2 my-auto p-2">
          <div
            style={{ cursor: "pointer" }}
            onClick={() => navigate(`/item/details/${itemId}`)}
          >
            <h3 className="ms-4">{itemName}</h3>
          </div>
          <p className="ms-4 mt-4">
            <b>OrderPrice:</b> ₹{orderPrice}
          </p>
          <p className="ms-4 mt-1">
            <b>ItemCount:</b> {itemCount}
          </p>
          <p className="ms-4 mt-1">
            <b>Ordered On:</b> {creationDate.substring(0, 10)}
          </p>
          <p className="ms-4 mt-1">
            <b>Address:</b> {address}
          </p>
        </div>

        <div className="editor">
          <div
            className="position-absolute"
            style={{ top: "40%", right: "10%" }}
          >
            {cancelButton}
          </div>
        </div>
      </div>
    </div>
  );
}
