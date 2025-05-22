import { Box, Paper, Typography } from "@mui/material";
import { useEffect, useState } from "react";
import axios from "../api/axios";

const Dashboard = () => {
  const [orderCount, setOrderCount] = useState(0);

  useEffect(() => {
    axios.get("/api/orders").then((res) => {
      setOrderCount(res.data.length);
    });
  }, []);

  return (
    <Box>
      <Typography variant="h4" gutterBottom>Welcome to DropBaba Admin</Typography>
      <Paper sx={{ p: 3, width: 300 }}>
        <Typography variant="h6">Total Orders</Typography>
        <Typography variant="h4" color="primary">{orderCount}</Typography>
      </Paper>
    </Box>
  );
};

export default Dashboard;
