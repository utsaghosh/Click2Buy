import React, { useEffect, useState } from "react";
import StarRoundedIcon from "@mui/icons-material/StarRounded";
import { toast } from "react-toastify";
import { useNavigate } from "react-router-dom";
import cartService from "../../service/CartService";

export default function CartItem({
  id,
  count,
  removeButton,
  addButton,
  deleteButton,
}) {
  const [cartItem, setCartItem] = useState({});
  const [change, setChange] = useState(true);
  const {
    cartItemId,
    creationDate,
    itemCount,
    itemId,
    itemName,
    itemImage,
    itemPrice,
    userId,
    averageRating,
  } = cartItem;

  const navigate = useNavigate();

  useEffect(() => {
    getCartItem();
  }, [count]);

  const getCartItem = async () => {
    let response = null;

    await cartService
      .getCartItemById(id)
      .then((res) => {
        response = res;
        setCartItem(response.data);
      })
      .catch((error) => {
        // error is handled in catch block
        if (error.response) {
          // status code out of the range of 2xx
          let data = error.response.data;
          let status = error.response.status;
          toast.error(`Error ${status}: ${data.errorMessage}`);
        } else if (error.request) {
          // The request was made but no response was received
          console.log(error.request);
        } else {
          // Error on setting up the request
          console.log("Error" + error.message);
        }
      });

    if (!response) return;
  };

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
            <h3 className="ms-3">{itemName}</h3>
          </div>
          <div className="ms-3 mt-2">
            <span className="mt-3">
              <StarRoundedIcon style={{ color: "yellow" }} />
            </span>
            <span className="ms-1 mt-4">{averageRating}</span>
          </div>
          <div className="ms-3 mt-2">
            <h3 className="ms-1 mt-4">₹{itemPrice}</h3>
          </div>
        </div>

        <div className="editor">
          <div
            className="position-absolute"
            style={{ top: "35px", right: "220px" }}
          >
            {addButton}
          </div>
          <div
            className="position-absolute"
            style={{ top: "40px", right: "202px" }}
          >
            <h4>{count * 1}</h4>
          </div>

          {itemCount * 1 > 1 && (
            <div
              className="position-absolute"
              style={{ top: "35px", right: "120px" }}
            >
              {removeButton}
            </div>
          )}

          <div
            className="position-absolute"
            style={{ top: "90px", right: "165px" }}
          >
            {deleteButton}
          </div>
        </div>
      </div>
    </div>
  );
}
