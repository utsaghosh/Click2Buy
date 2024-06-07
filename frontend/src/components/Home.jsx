import Login from "./auth/Login.jsx";
import ItemList from "./item/ItemList.jsx";
import { useUserContext } from "../context/UserContext.jsx";

function Home() {
  // const {user, setUser} = useUserContext();
  const user = JSON.parse(sessionStorage.getItem("user"));

  if (!user) {
    return <Login />;
  }

  return <ItemList />;
}

export default Home;
