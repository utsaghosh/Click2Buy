import axios from "axios";

const client = axios.create({
  baseURL: "http://localhost:8080/users",
});

class UserService {
  async loginUser(data) {
    try {
      return client.post("/auth", data);
    } catch (error) {
      console.log(error);
    }
  }

  //post
  async signupUser(data) {
    try {
      return client.post("/add", data);
    } catch (error) {
      console.log(error);
    }
  }

  //put
  async updateUser({ userId, userData }) {
    try {
      return client.put(`/update/${userId}`, userData);
    } catch (error) {
      console.log(error);
    }
  }

  async getUserCart(userId) {
    try {
      return client.get(`/getcart/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getUser(userId) {
    try {
      return client.get(`/get/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  //delete
  async deleteUser(userId) {
    try {
      return client.delete(`/delete/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }
}

const userService = new UserService();
export default userService;
