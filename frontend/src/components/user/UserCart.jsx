import { useEffect, useState } from "react";
import cartService from "../../service/CartService";
import CartItem from "../cart/CartItem";
import { useUserContext } from "../../context/UserContext";
import Navbar from "../Navbar";
import { toast } from "react-toastify";
import Button from "@mui/material/Button";
import AddCircleIcon from "@mui/icons-material/AddCircle";
import RemoveCircleIcon from "@mui/icons-material/RemoveCircle";

import orderService from "../../service/OrderService";
import { useNavigate } from "react-router-dom";

export default function UserCart() {
  const [cart, setCart] = useState([]);
  const [change, setChange] = useState(true);
  const [orderTotal, setOrderTotal] = useState(0);

  const navigate = useNavigate();

  // const {user, setUser} = useUserContext();

  const user = JSON.parse(sessionStorage.getItem("user"));
  const { userId, userName, email, password, address, gender, age } = user;

  useEffect(() => {
    getUserCart();
    getOrderTotal();
  }, [change]);

  useEffect(() => {
    getOrderTotal();
  }, [cart]);

  const getUserCart = async () => {
    let response = null;

    await cartService
      .getUserCart(userId)
      .then((res) => {
        response = res;
        setCart(response.data);
      })
      .catch((error) => {
        // error is handled in catch block
        if (error.response) {
          // status code out of the range of 2xx
          let data = error.response.data;
          toast.warn(data.errorMessage);
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

  const getOrderTotal = () => {
    let sum = 0;
    cart.forEach(
      (cartItem) => (sum += cartItem.itemPrice * 1 * cartItem.itemCount)
    );
    setOrderTotal(sum);
  };

  const placeOrder = async () => {
    let response = null;

    await orderService
      .placeOrder(userId)
      .then((res) => {
        response = res;
        toast.success("Order Placed Successfully !");
        setChange(!change);
        setTimeout(() => {
          navigate("/user/orders");
        }, 1000);
      })
      .catch((error) => {
        // error is handled in catch block
        if (error.response) {
          // status code out of the range of 2xx
          let data = error.response.data;
          toast.error(data.errorMessage);
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

  const removeOne = async (cartItemId) => {
    let response = null;
    const data = {
      cartItemId: cartItemId,
      count: 1,
    };

    await cartService
      .removeFromCart(data)
      .then((res) => {
        response = res;
        toast.success("Removed from cart!");
        setChange(!change);
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

  const addOne = async (itemId) => {
    let response = null;
    const userId = user.userId;
    const data = { itemId: itemId, userId: userId, count: 1 };

    await cartService
      .addToCart(data)
      .then((res) => {
        response = res;
        toast.success("Added to cart!");
        setChange(!change);
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

  const deleteCartItem = async (cartItemId) => {
    let response = null;

    await cartService
      .deleteFromCart(cartItemId)
      .then((res) => {
        response = res;
        toast.success("Deleted from cart");
        setChange(!change);
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
          console.log("Error", error.message);
        }
      });

    if (!response) return;
  };

  return (
    <>
      <Navbar />
      <div className="d-flex justify-content-center my-4">
        <h2>Your Cart:</h2>
      </div>
      {cart.length > 0 ? (
        <div>
          {cart.map((cartItem) => (
            <CartItem
              id={cartItem.cartItemId * 1}
              count={cartItem.itemCount * 1}
              key={cartItem.cartItemId}
              removeButton={
                <Button
                  startIcon={
                    <RemoveCircleIcon color="action" fontSize="large" />
                  }
                  size="20"
                  onClick={() => removeOne(cartItem.cartItemId * 1)}
                />
              }
              addButton={
                <Button
                  startIcon={<AddCircleIcon color="success" fontSize="large" />}
                  size="20"
                  onClick={() => addOne(cartItem.itemId * 1)}
                />
              }
              deleteButton={
                <Button
                  variant="contained"
                  color="error"
                  onClick={() => deleteCartItem(cartItem.cartItemId * 1)}
                >
                  Delete
                </Button>
              }
            />
          ))}
          <div className="justify-content-center d-flex align-items-center mx-5 my-5">
            <h3> Order Total = </h3>
            <h3>₹{orderTotal}</h3>
          </div>
          <div className="justify-content-center d-flex align-items-center my-5">
            <Button variant="contained" color="success" onClick={placeOrder}>
              Place Order
            </Button>
          </div>
        </div>
      ) : (
        <div className="justify-content-center d-flex align-items-center mx-5 my-5">
          <p>Your Cart is Empty!</p>
        </div>
      )}
    </>
  );
}
