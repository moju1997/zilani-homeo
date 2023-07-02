import React from "react";
import { useSelector } from "react-redux";
import makeStyles from "@material-ui/styles/makeStyles";
import {
  Create,
  Edit,
  List,
  SimpleForm,
  TextInput,
  TextField,
  required,
  minLength,
  Datagrid,
  EditButton,
  DeleteButton,
} from "jazasoft";
// import hasPrivilege from "../../utils/hasPrivilege";
// import Forbidden from "../../components/Forbidden";

export const CreateMedicinePower = (props) => {
  return (
    <Create {...props}>
      <SimpleForm redirect="home">
        <TextInput source="name" validate={[required(), minLength(2)]} />
        <TextInput source="description" />
      </SimpleForm>
    </Create>
  );
};

export const EditMedicinePower = (props) => {
  const record = useSelector(
    (state) =>
      state.jazasoft.resources[props.resource] &&
      state.jazasoft.resources[props.resource].data &&
      state.jazasoft.resources[props.resource].data[props.id]
  );

  return (
    <Edit {...props}>
      <SimpleForm redirect="home" form="record-form-edit">
        <TextInput
          source="name"
          validate={[required(), minLength(2)]}
          options={{ fullWidth: true, disabled: record?.isReferenced }}
        />
        <TextInput source="description" />
      </SimpleForm>
    </Edit>
  );
};

const useHomeStyles = makeStyles((theme) => ({
  buttonEdit: {
    width: theme.spacing(14),
  },
  buttonDelete: {
    width: theme.spacing(16),
  },
}));

export const MedicinePowerHome = (props) => {
  const classes = useHomeStyles();
  return (
    <List {...props}>
      <Datagrid>
        <TextField source="name" />
        <TextField source="description" />
        <EditButton cellClassName={classes.buttonEdit} />
        <DeleteButton cellClassName={classes.buttonDelete} />
      </Datagrid>
    </List>
  );
};
