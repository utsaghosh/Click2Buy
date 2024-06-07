import React from "react";
import StarRoundedIcon from "@mui/icons-material/StarRounded";
import AddCircleIcon from "@mui/icons-material/AddCircle";
import RemoveCircleIcon from "@mui/icons-material/RemoveCircle";
import cartService from "../../service/CartService";
import Button from "@mui/material/Button";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

export default function ItemCard({ item }) {
  const {
    itemId,
    itemName,
    itemPrice,
    itemDescription,
    itemImage,
    averageRating,
  } = item;

  const [itemCount, setItemCount] = useState(1);

  // const {user,setUser} = useUserContext();
  const user = JSON.parse(sessionStorage.getItem("user"));

  const navigate = useNavigate();

  const AddToCart = async () => {
    let response = null;
    const userId = user.userId;
    const data = { itemId: itemId, userId: userId, count: itemCount };

    await cartService
      .addToCart(data)
      .then((res) => {
        response = res;
        toast.success("Added to cart!");
      })
      .catch((error) => {
        // error is handled in catch block
        if (error.response) {
          // status code out of the range of 2xx
          let data = error.response.data;
          let status = error.response.status;
          toast.warn(`Warn ${status}: ${data.errorMessage}`);
        } else if (error.request) {
          // The request was made but no response was received
          console.log(error.request);
        } else {
          // Error on setting up the request
          console.log("Error", error.message);
        }
      });

    if (!response) return;

    return response.data;
  };

  const increment = () => {
    setItemCount(itemCount + 1);
  };
  const decrement = () => {
    setItemCount(itemCount - 1);
  };

  return (
    <div className="d-flex flex-row justify-content-center">
      <div
        className="container d-flex flex-row border border-2 rounded-2 m-1 p-1 position-relative"
        style={{ width: "90%", height: "8%" }}
      >
        <div className="image">
          <img
            src={itemImage}
            alt="item poster"
            style={{ width: "200px", height: "200px" }}
          ></img>
        </div>

        <div className="mx-4 my-auto p-2">
          <div
            style={{ cursor: "pointer" }}
            className="ms-3"
            onClick={() => navigate(`/item/details/${itemId}`)}
          >
            <h3>{itemName}</h3>
          </div>
          <div className="mt-4 ms-3">
            <span className="mt-3">
              <StarRoundedIcon style={{ color: "yellow" }} />
            </span>
            <span className="ms-1 mt-5">{averageRating}</span>
          </div>
          <div className="ms-3 mt-2">
            <h3 className="ms-1 mt-4">₹{itemPrice}</h3>
          </div>
        </div>

        <div className="editor mx-3 my-auto">
          <div
            className="position-absolute"
            style={{ top: "35px", right: "220px" }}
          >
            <Button
              startIcon={<AddCircleIcon color="success" fontSize="large" />}
              size="20"
              onClick={increment}
            />
          </div>
          <div
            className="position-absolute"
            style={{ top: "40px", right: "202px" }}
          >
            <h4>{itemCount * 1}</h4>
          </div>

          {itemCount * 1 > 0 && (
            <div
              className="position-absolute"
              style={{ top: "35px", right: "120px" }}
            >
              <Button
                startIcon={<RemoveCircleIcon color="action" fontSize="large" />}
                size="20"
                onClick={decrement}
              />
            </div>
          )}

          {itemCount * 1 > 0 && (
            <div
              className="position-absolute"
              style={{ top: "90px", right: "148px" }}
            >
              <Button variant="contained" color="success" onClick={AddToCart}>
                Add to cart
              </Button>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
