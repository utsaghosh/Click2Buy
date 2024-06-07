import React from "react";
import { useNavigate } from "react-router-dom";
import UserProfileButton from "./user/UserProfileButton";
import ShoppingCartOutlinedIcon from "@mui/icons-material/ShoppingCartOutlined";

export default function Navbar({ search }) {
  const navigate = useNavigate();
  // const {user,setUser} = useUserContext();
  const user = JSON.parse(sessionStorage.getItem("user"));

  return (
    <nav className="navbar bg-dark justify-content-between">
      <div onClick={() => navigate("/")}>
        <a className="navbar-brand text-light ms-3" href="#">
          Click2Buy
        </a>
      </div>

      <div className="d-flex align-items-center">
        <div className="mx-3" style={{ cursor: "pointer" }}>
          <ShoppingCartOutlinedIcon
            color="primary"
            fontSize="large"
            onClick={() => {
              navigate("/user/cart");
            }}
          />
        </div>

        <div
          className="me-3"
          style={{ cursor: "pointer" }}
          id="navbarSupportedContent"
          onClick={() => {
            navigate("/user/profile");
          }}
        >
          <UserProfileButton text={user.userName.charAt(0)} />
        </div>
      </div>
    </nav>
  );
}
