import axios from "axios";

const client = axios.create({
  baseURL: "http://localhost:8080/reviews",
});

class ReviewService {
  async addReview(review) {
    try {
      return client.post("/addreview", review);
    } catch (error) {
      console.log(error);
    }
  }

  async updateReview({ reviewId, review }) {
    try {
      return client.put(`/updatereview/${reviewId}`, review);
    } catch (error) {
      console.log(error);
    }
  }

  async getReviewOfItemByUser({ itemId, userId }) {
    try {
      return client.get(`/getreview/${itemId}/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getAllReviewsOfItem(itemId) {
    try {
      return client.get(`/getitemreviews/${itemId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getAllReviewsByUser(userId) {
    try {
      return client.get(`/getreviewsbyuser/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getReview(reviewId) {
    try {
      return client.get(`/getbyid/${reviewId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async deleteReview(reviewId) {
    try {
      client.delete(`/delete/${reviewId}`);
    } catch (error) {
      console.log(error);
    }
  }
}

const reviewService = new ReviewService();
export default reviewService;
