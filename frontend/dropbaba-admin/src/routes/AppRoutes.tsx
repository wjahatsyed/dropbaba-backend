import { Routes, Route } from "react-router-dom";
import AdminLayout from "../layouts/AdminLayout.tsx";
import Dashboard from "../pages/Dashboard";
import Orders from "../pages/Orders";

const AppRoutes = () => (
  <Routes>
    <Route element={<AdminLayout />}>
      <Route path="/" element={<Dashboard />} />
      <Route path="/orders" element={<Orders />} />
    </Route>
  </Routes>
);

export default AppRoutes;
