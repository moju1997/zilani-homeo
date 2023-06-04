import React from "react";

import { makeStyles } from "@material-ui/core/styles";
import UploadIcon from "mdi-material-ui/Upload";
import { List, Datagrid, TextField, ShowButton, EditButton, CreateButton, Button } from "jazasoft";

const useStyles = makeStyles(theme => ({
  buttonEdit: {
    width: theme.spacing(14)
  },
  buttonDelete: {
    width: theme.spacing(16)
  }
}));

const Actions = ({ basePath, history }) => (
  <div>
    <CreateButton basePath={basePath} showLabel={false} />
    <Button showLabel={false} label="Upload" onClick={() => history.push("/users/upload")}>
      <UploadIcon />
    </Button>
  </div>
);

const UserHome = props => {
  const classes = useStyles();
  return (
    <List
      searchKeys={["fullName", "username", "email", "roles"]}
      actions={<Actions history={props.history} />}
      {...props}>
      <Datagrid>
        <TextField source="fullName" />
        <TextField source="email" />
        <ShowButton cellClassName={classes.buttonEdit} />
        <EditButton cellClassName={classes.buttonEdit} />
        {/* <DeleteButton cellClassName={classes.buttonDelete} /> */}
      </Datagrid>
    </List>
  );
};
export default UserHome;
