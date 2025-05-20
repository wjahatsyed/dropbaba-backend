import { useEffect, useState } from "react";
import axios from "../api/axios";

const Orders = () => {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    axios.get("/api/orders").then(res => setOrders(res.data));
  }, []);

  return (
    <div>
      <h2>Orders</h2>
      <ul>
        {orders.map((order: any) => (
          <li key={order.id}>Order #{order.id} - {order.totalAmount}</li>
        ))}
      </ul>
    </div>
  );
};

export default Orders;
