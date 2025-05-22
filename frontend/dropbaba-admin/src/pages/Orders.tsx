import { Box, Typography } from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import type { GridColDef } from "@mui/x-data-grid";

import { useEffect, useState } from "react";
import axios from "../api/axios";

interface Order {
  id: number;
  userId: number;
  vendorId: number;
  totalAmount: number;
  status: string;
}

const Orders = () => {
  const [orders, setOrders] = useState<Order[]>([]);

  useEffect(() => {
    axios.get("/api/orders")
      .then(res => setOrders(res.data))
      .catch(err => console.error("Failed to fetch orders", err));
  }, []);

  const columns: GridColDef[] = [
    { field: "id", headerName: "Order ID", width: 100 },
    { field: "userId", headerName: "User ID", width: 100 },
    { field: "vendorId", headerName: "Vendor ID", width: 100 },
    { field: "totalAmount", headerName: "Total (PKR)", width: 150 },
    { field: "status", headerName: "Status", width: 150 },
  ];

  return (
    <Box p={2}>
      <Typography variant="h5" gutterBottom>All Orders</Typography>
      <div style={{ height: 500, width: "100%" }}>
        <DataGrid
          rows={orders}
          columns={columns}
          pageSizeOptions={[10, 25, 50]}
          pagination
          disableRowSelectionOnClick
        />
      </div>
    </Box>
  );
};

export default Orders;
