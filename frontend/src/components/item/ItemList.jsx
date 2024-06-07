import React, { useEffect, useState } from "react";
import ItemCard from "./ItemCard.jsx";
import itemService from "../../service/ItemService.jsx";
import { toast } from "react-toastify";
import Navbar from "../Navbar.jsx";
import Footer from "../Footer.jsx";

export default function ItemList() {
  const [items, setItems] = useState([]);
  const [search, setSearch] = useState("");

  useEffect(() => {
    if (search) {
      getSearchedItems();
    } else {
      getItems();
    }
  }, [search]);

  const getItems = async () => {
    let response = null;

    await itemService
      .getAllItems()
      .then((res) => {
        response = res;
        setItems(response.data);
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

  const getSearchedItems = async () => {
    let response = null;

    await itemService
      .getItemsMatchingName(search)
      .then((res) => {
        response = res;
        setItems(response.data);
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
    <>
      <Navbar />
      <div className="d-flex justify-content-center mx-auto my-2">
        <input
          className="form-control border border-success me-2"
          style={{ width: "450px" }}
          type="text"
          placeholder="Search"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />
      </div>
      <ul className="list-group mt-3">
        <li className="list-group-item justify-content-between d-flex align-items-center">
          <div
            className="d-flex flex-column justify-content-between"
            style={{ width: "100%" }}
          >
            {items.map((item) => (
              <ItemCard item={item} key={item.itemId} />
            ))}
          </div>
        </li>
      </ul>
      {/* <Footer /> */}
    </>
  );
}
