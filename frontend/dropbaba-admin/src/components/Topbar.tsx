import { AppBar, Toolbar, Typography } from "@mui/material";

const Topbar = () => {
  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6">DropBaba Admin</Typography>
      </Toolbar>
    </AppBar>
  );
};

export default Topbar;
