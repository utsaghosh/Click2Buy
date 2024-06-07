import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import itemService from "../../service/ItemService";
import reviewService from "../../service/ReviewService";
import orderService from "../../service/OrderService";
import { toast } from "react-toastify";
import ReviewCard from "../review/ReviewCard";
import { useUserContext } from "../../context/UserContext";
import AddReviewModal from "../review/AddReviewModal";
import ratingService from "../../service/RatingService";
import Rating from "@mui/material/Rating";
import Navbar from "../Navbar";
import RatingViewCard from "../rating/RatingViewCard";
import AddCircleIcon from "@mui/icons-material/AddCircle";
import RemoveCircleIcon from "@mui/icons-material/RemoveCircle";
import cartService from "../../service/CartService";
import Button from "@mui/material/Button";

export default function Details() {
  const { id } = useParams();
  // const {user,setUser} = useUserContext();
  const user = JSON.parse(sessionStorage.getItem("user"));

  const [item, setItem] = useState({});
  const [userReview, setUserReview] = useState({});
  const [userRating, setUserRating] = useState({});
  const [allReviews, setAllReviews] = useState([]);
  const [allRatings, setAllRatings] = useState([]);
  const [ordered, setOrdered] = useState(false);

  const [tempScore, setTempScore] = useState(0);
  const [itemCount, setItemCount] = useState(1);

  const {
    itemId,
    itemName,
    itemPrice,
    itemDescription,
    itemImage,
    averageRating,
  } = item;

  useEffect(() => {
    getItem();
    getOrderStatus();
    getAllReviews();
    getUserReview();
    getAllRatings();
    getUserRating();
  }, []);

  const getOrderStatus = async () => {
    let response = null;
    const userId = user.userId;
    const itemId = id;
    const data = { itemId, userId };

    await orderService
      .checkInUserOrders(data)
      .then((res) => {
        response = res;
        setOrdered(String(response.data).toLowerCase() === "true");
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

  const getItem = async () => {
    let response = null;

    await itemService
      .getItemDetails(id)
      .then((res) => {
        response = res;
        setItem(response.data);
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

  const getAllReviews = async () => {
    let response = null;

    await reviewService
      .getAllReviewsOfItem(id)
      .then((res) => {
        response = res;
        setAllReviews(response.data);
      })
      .catch((error) => {
        // error is handled in catch block
        if (error.response) {
          // status code out of the range of 2xx
          console.log(error.response);
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

  const getUserReview = async () => {
    let response = null;
    const userId = user.userId;
    const itemId = id;
    const data = { itemId, userId };

    await reviewService
      .getReviewOfItemByUser(data)
      .then((res) => {
        response = res;
        setUserReview(response.data);
      })
      .catch((error) => {
        // error is handled in catch block
        if (error.response) {
          // status code out of the range of 2xx
          console.log(error.response);
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

  const getAllRatings = async () => {
    let response = null;

    await ratingService
      .getAllRatingsOfItem(id)
      .then((res) => {
        response = res;
        setAllRatings(response.data);
      })
      .catch((error) => {
        // error is handled in catch block
        if (error.response) {
          // status code out of the range of 2xx
          console.log(error.response);
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

  const getUserRating = async () => {
    let response = null;
    const userId = user.userId;
    const itemId = id;
    const data = { itemId, userId };

    await ratingService
      .getRatingOfItemByUser(data)
      .then((res) => {
        response = res;
        setUserRating(response.data);
        setTempScore(response.data.score * 1);
      })
      .catch((error) => {
        // error is handled in catch block
        if (error.response) {
          // status code out of the range of 2xx
          console.log(error.response);
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

  const AddRating = async () => {
    let response = null;
    const userName = user.userName;
    const userId = user.userId;
    const itemId = id;
    const score = tempScore;
    const data = {
      itemId: itemId,
      userId: userId,
      userName: userName,
      score: score,
    };

    await ratingService
      .addRating(data)
      .then((res) => {
        response = res;
        toast.success("Rating added successfully");
        setTempScore(response.data.score);
        setUserRating(response.data);
        setTimeout(() => {
          window.location.reload(true);
        }, 300);
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

  const UpdateRating = async () => {
    let response = null;
    const ratingId = userRating.ratingId;
    const userName = userRating.userName;
    const userId = userRating.userId;
    const itemId = id;
    const score = tempScore * 1;
    const rating = {
      itemId: itemId,
      userName: userName,
      userId: userId,
      score: score,
    };
    const data = { ratingId, rating };

    await ratingService
      .updateRating(data)
      .then((res) => {
        response = res;
        toast.success("Rating updated successfully");
        setTempScore(response.data.score * 1);
        setUserRating(response.data);
        setTimeout(() => {
          window.location.reload(true);
        }, 300);
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
    <>
      <Navbar search={false} />
      <div
        className="card mx-5 my-5 "
        style={{ Width: "90%", backgroundColor: "#fafaed" }}
      >
        <div className="row g-0">
          <div className="col-md-4">
            <img
              src={itemImage}
              className="img-fluid rounded-start"
              alt="item poster"
            />
          </div>
          <div className="col-md-8">
            <div className="card-body m-5">
              <h1 className="card-title">{itemName}</h1>
              <div className="details m-3 mt-5">
                <p className="card-text fw-bold mb-4">
                  Price: {"Rs. "}
                  <span className="text-muted ms-1">₹{itemPrice}</span>
                </p>
              </div>
              <p className="card-text fw-bold mt-5">{itemDescription}</p>
            </div>
          </div>
        </div>
      </div>

      <div className="d-flex justify-content-center my-4">
        <div className="my-2 mx-2">
          <Button
            startIcon={<AddCircleIcon color="success" fontSize="large" />}
            size="20"
            onClick={increment}
          />
        </div>
        <div className="my-3 mx-2">
          <h4>{itemCount * 1}</h4>
        </div>

        {itemCount * 1 > 0 && (
          <div className="my-2 mx-3">
            <Button
              startIcon={<RemoveCircleIcon color="action" fontSize="large" />}
              size="20"
              onClick={decrement}
            />
          </div>
        )}

        {itemCount * 1 > 0 && (
          <div className="my-3 mx-2">
            <Button variant="contained" color="success" onClick={AddToCart}>
              Add to cart
            </Button>
          </div>
        )}
      </div>

      {ordered === true ? (
        Object.keys(userRating).length > 0 ? (
          <div className="d-flex justify-content-center my-4">
            <h5>Your Rating:</h5>
            <div className="d-flex mx-4">
              <Rating
                name="simple-controlled"
                value={tempScore * 1}
                onChange={(event, newValue) => {
                  setTempScore(newValue * 1);
                }}
              />
              <div className="mx-4">
                <button
                  className="btn btn-success btn-sm"
                  onClick={UpdateRating}
                >
                  Update rating
                </button>
              </div>
            </div>
          </div>
        ) : (
          <div className="d-flex justify-content-center my-4">
            <h5>You haven't rated this item</h5>
            <div className="d-flex mx-4">
              <Rating
                name="simple-controlled"
                value={tempScore}
                onChange={(event, newValue) => {
                  setTempScore(newValue * 1);
                }}
              />
              <div className="mx-4">
                <button className="btn btn-success btn-sm" onClick={AddRating}>
                  Add rating
                </button>
              </div>
            </div>
          </div>
        )
      ) : (
        <div></div>
      )}

      <div className="mx-5">
        <h5>Average Rating: {averageRating * 1}/5</h5>
        <div className="d-flex mx-4">
          <Rating name="read-only" precision={0.5} value={averageRating * 1} />
        </div>
      </div>

      <div>
        <RatingViewCard itemRatings={allRatings}></RatingViewCard>
      </div>

      {ordered === true ? (
        Object.keys(userReview).length > 0 ? (
          <div className="my-5 ms-5">
            <h5>Your Review:</h5>
            <div className="my-2">
              <ReviewCard
                review={userReview}
                editable={true}
                deletable={true}
              />
            </div>
          </div>
        ) : (
          <div className="my-5">
            <div className="ms-5">
              <h5>You haven't reviewed this item yet</h5>
            </div>
            <div className="my-3 ms-5">
              <AddReviewModal
                itemId={id}
                userId={user.userId}
                userName={user.userName}
              />
            </div>
          </div>
        )
      ) : (
        <div></div>
      )}

      {allReviews.length > 0 ? (
        <div className="my-5">
          <div className="ms-5">
            <h5>All Reviews:</h5>
          </div>
          <div className="d-flex ms-5 my-2">
            {allReviews.map((review) => (
              <ReviewCard
                review={review}
                key={review.reviewId}
                editable={false}
                deletable={false}
              />
            ))}
          </div>
        </div>
      ) : (
        <div className="mx-5 my-5">
          <h5>No reviews for this item yet</h5>
        </div>
      )}
      {/* <Footer /> */}
    </>
  );
}
