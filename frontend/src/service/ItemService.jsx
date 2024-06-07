import axios from "axios";

const client = axios.create({
  baseURL: "http://localhost:8080/items",
});

class ItemService {
  async getAllItems() {
    try {
      return client.get("/getallitems");
    } catch (error) {
      console.log(error);
    }
  }

  async getItemDetails(itemId) {
    try {
      return client.get(`/getbyid/${itemId}`);
    } catch (error) {
      console.log(error);
    }
  }

  async getItemsMatchingName(name) {
    try {
      return client.get(`/getmatchitem/${name}`);
    } catch (error) {
      console.log(error);
    }
  }

  async addItem(data) {
    try {
      return axios.post("/additem", data);
    } catch (error) {
      console.log(error);
    }
  }

  async deleteItem(itemId) {
    try {
      return client.delete(`/delete/${itemId}`);
    } catch (error) {
      console.log(error);
    }
  }
}

const itemService = new ItemService();
export default itemService;
