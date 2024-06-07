import { useEffect, useState } from "react";
import { useUserContext } from "../../context/UserContext";
import Navbar from "../Navbar";
import { toast } from "react-toastify";
import Button from "@mui/material/Button";

import orderService from "../../service/OrderService";
import OrderItem from "../order/OrderItem";

export default function UserCart() {
  const [orders, setOrders] = useState([]);
  const [change, setChange] = useState(true);

  // const {user, setUser} = useUserContext();

  const user = JSON.parse(sessionStorage.getItem("user"));
  const { userId, userName, email, password, address, gender, age } = user;

  useEffect(() => {
    getUserOrders();
  }, [change]);

  const getUserOrders = async () => {
    let response = null;

    await orderService
      .getOrdersByUser(userId)
      .then((res) => {
        response = res;
        setOrders(response.data);
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

  const cancelOrder = async (orderId) => {
    let response = null;

    await orderService
      .cancelOrder(orderId)
      .then((res) => {
        response = res;
        if (response) {
          toast.success("Order cancelled successfully");
        } else {
          toast.warn("This order cannot be deleted");
        }

        setChange(!change);
      })
      .catch((error) => {
        // error is handled in catch block
        if (error.response) {
          // status code out of the range of 2xx
          let data = error.response.data;
          let status = error.response.status;
          toast.warn(`Error ${status}: ${data.errorMessage}`);
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
        <h2>Your Orders:</h2>
      </div>
      {orders.length > 0 ? (
        <div className="d-flex flex-column justify-content-center my-1">
          {orders.map((orderItem) => (
            <OrderItem
              orderItem={orderItem}
              key={orderItem.orderItemId}
              cancelButton={
                <Button
                  variant="contained"
                  color="error"
                  onClick={() => cancelOrder(orderItem.orderItemId * 1)}
                >
                  Cancel
                </Button>
              }
            />
          ))}
        </div>
      ) : (
        <div className="justify-content-center d-flex align-items-center mx-5 my-5">
          <p>You haven't placed any orders yet!</p>
        </div>
      )}
    </>
  );
}
