import axios from "axios";

const client = axios.create({
  baseURL: "http://localhost:8080/carts",
});

class CartService {
  async addToCart(data) {
    try {
      return client.post("/add", data);
    } catch (error) {
      console.log(error);
    }
  }

  async removeFromCart({ cartItemId, count }) {
    try {
      return client.post(`/remove/${cartItemId}/${count}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getUserCart(userId) {
    try {
      return client.get(`/getusercart/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getCartItemId({ itemId, userId }) {
    try {
      return client.get(`/getid/${itemId}/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getCartItemById(cartItemId) {
    try {
      return client.get(`/getbyid/${cartItemId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async checkInUserCart({ itemId, userId }) {
    try {
      return client.get(`/check/${itemId}/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async deleteFromCart(cartItemId) {
    try {
      return client.delete(`/delete/${cartItemId}`);
    } catch (error) {
      console.log(error);
    }
  }
}

const cartService = new CartService();
export default cartService;
