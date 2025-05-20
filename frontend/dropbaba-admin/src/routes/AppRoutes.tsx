import { Routes, Route } from "react-router-dom";
import Dashboard from "../pages/Dashboard.tsx";
import Orders from "../pages/Orders.tsx";

const AppRoutes = () => (
  <Routes>
    <Route path="/" element={<Dashboard />} />
    <Route path="/orders" element={<Orders />} />
  </Routes>
);

export default AppRoutes;
