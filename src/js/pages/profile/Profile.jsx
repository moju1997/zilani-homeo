import React from "react";
import { useSelector, useDispatch } from "react-redux";

import Card from "@material-ui/core/Card";
import Grid from "@material-ui/core/Grid";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemSecondaryAction from "@material-ui/core/ListItemSecondaryAction";
import ListItemText from "@material-ui/core/ListItemText";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";

import { PageHeader, PageFooter, Button, TextInput, changePassword } from "jazasoft";

import FormDialog from "../../components/FormDialog";

import { Roles } from "../../utils/types";

const minLength = 8;
const maxLength = 20;
let regexPasswd = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;

const validate = values => {
  const errors = {};
  if (!values.oldPassword) {
    errors.oldPassword = "Old Password Required.";
  }

  if (!values.newPassword) {
    errors.newPassword = "New Password Required.";
  } else if (values.newPassword.length < minLength) {
    errors.newPassword = `Password must be at least ${minLength} characters long.`;
  } else if (values.newPassword.length > maxLength) {
    errors.newPassword = `Password must be at most ${maxLength} characters long.`;
  } else if (!regexPasswd.test(values.newPassword)) {
    errors.newPassword =
      "Password must contain at least one number, one special character and have a mixture of uppercase and lowercase letters.";
  }

  if (!values.confirmNewPassword) {
    errors.confirmNewPassword = "Confirm New Password Required.";
  }

  if (values.newPassword !== values.confirmNewPassword) {
    errors.confirmNewPassword = "Password do not match.";
  }

  return errors;
};

const useStyles = makeStyles(theme => ({
  content: {
    marginLeft: theme.spacing(2),
    marginRight: theme.spacing(2),
    marginBottom: theme.spacing(2),
    padding: theme.spacing(2)
  }
}));

const Profile = () => {
  const classes = useStyles();
  const dispatch = useDispatch();

  const [dialogActive, setDialogActive] = React.useState(false);

  const authState = useSelector(state => state.jazasoft.auth || {});

  const onSubmit = values => {
    dispatch(changePassword({ ...values, username: authState.username }));
    setDialogActive(false);
  };

  console.log(authState);
  const roleList = authState.roles
    ?.split(",")
    .map(e => e?.trim())
    .map(role => Roles[Object.keys(Roles).find(key => Roles[key].id === role)]?.name || "");

  let data = [
    { label: "Full Name", value: authState.fullName },
    { label: "User Name", value: authState.username },
    { label: "Email", value: authState.email || "-" },
    { label: "Mobile", value: authState.mobile },
    { label: "Roles", value: roleList?.join(", ") }
  ];

  return (
    <div>
      <PageHeader title="My Profile" />

      <FormDialog
        title="Change Password"
        open={dialogActive}
        onSubmit={onSubmit}
        onClose={() => setDialogActive(false)}
        formProps={{ validate }}>
        <TextInput source="oldPassword" type="password" xs={12} fullWidth options={{ fullWidth: true }} />
        <TextInput source="newPassword" type="password" xs={12} fullWidth options={{ fullWidth: true }} />
        <TextInput source="confirmNewPassword" type="password" xs={12} fullWidth options={{ fullWidth: true }} />
      </FormDialog>

      <Card className={classes.content} elavation={4} square={true}>
        <Grid container>
          <Grid item xs={12} sm={8}>
            <List>
              {data.length > 0 &&
                data.map(e => (
                  <ListItem key={e.label} button>
                    <ListItemText secondary={e.label} secondaryTypographyProps={{ variant: "subtitle1" }} />
                    <ListItemSecondaryAction>
                      <Typography component="div">{e.value}</Typography>
                    </ListItemSecondaryAction>
                  </ListItem>
                ))}
            </List>
          </Grid>
        </Grid>

        <PageFooter>
          <Button variant="contained" color="primary" label="Change Password" onClick={() => setDialogActive(true)} />
        </PageFooter>
      </Card>
    </div>
  );
};

export default Profile;
