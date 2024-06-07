import axios from "axios";

const client = axios.create({
  baseURL: "http://localhost:8080/orders",
});

class OrderService {
  async placeOrder(userId) {
    try {
      return client.post(`/place/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getOrdersByUser(userId) {
    try {
      return client.get(`/getbyuser/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async checkInUserOrders({ itemId, userId }) {
    try {
      return client.get(`/check/${itemId}/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async cancelOrder(orderId) {
    try {
      return client.get(`/cancel/${orderId}`);
    } catch (error) {
      console.log(error);
    }
  }
}

const orderService = new OrderService();
export default orderService;
