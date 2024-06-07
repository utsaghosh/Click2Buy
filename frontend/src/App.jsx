import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./components/Home";
import Login from "./components/auth/Login";
import Signup from "./components/auth/Signup";
import UserProfile from "./components/user/UserProfile";
import UserUpdate from "./components/user/UserUpdate";
import Error404 from "./components/Error404";
import ItemList from "./components/item/ItemList";
import Details from "./components/item/Details";
import UserOrders from "./components/user/UserOrders";
import UserCart from "./components/user/UserCart";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/items" element={<ItemList />} />
        <Route path="/item/details/:id" element={<Details />} />
        <Route path="/user/profile" element={<UserProfile />} />
        <Route path="/user/orders" element={<UserOrders />} />
        <Route path="/user/cart" element={<UserCart />} />
        <Route path="/user/update" element={<UserUpdate />} />
        <Route path="*" element={<Error404 />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
