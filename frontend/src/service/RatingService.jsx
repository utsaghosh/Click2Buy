import axios from "axios";

const client = axios.create({
  baseURL: "http://localhost:8080/ratings",
});

class RatingService {
  async addRating(rating) {
    try {
      return client.post("/addrating", rating);
    } catch (error) {
      console.log(error);
    }
  }

  async updateRating({ ratingId, rating }) {
    try {
      return client.put(`/updaterating/${ratingId}`, rating);
    } catch (error) {
      console.log(error);
    }
  }

  async getRatingOfItemByUser({ itemId, userId }) {
    try {
      return client.get(`/getrating/${itemId}/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getAllRatingsOfItem(itemId) {
    try {
      return client.get(`/getitemratings/${itemId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getAllRatingsByUser(userId) {
    try {
      return client.get(`/getratingsbyuser/${userId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getRating(ratingId) {
    try {
      return client.get(`/getbyid/${ratingId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async deleteRating(ratingId) {
    try {
      client.delete(`/delete/${ratingId}`);
    } catch (error) {
      console.log(error);
    }
  }
}

const ratingService = new RatingService();
export default ratingService;
