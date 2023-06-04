import React from "react";

import { Edit, SimpleForm, TextInput, required, minLength, SelectInput } from "jazasoft";

import { Roles } from "../../utils/types";
import { inputOptions } from "../../utils/helpers";

const UserEdit = props => {
  return (
    <Edit {...props}>
      <SimpleForm redirect="home">
        <TextInput source="fullName" validate={[required(), minLength(2)]} {...inputOptions(4)} />
        <TextInput source="username" validate={[required()]} {...inputOptions(4)} />
        <TextInput source="email" validate={[required()]} {...inputOptions(4)} />
        <TextInput source="mobile" validate={[required()]} {...inputOptions(4)} />
        <SelectInput
          label="Role"
          source="roles"
          validate={[required()]}
          choices={Object.keys(Roles).map(key => Roles[key])}
          {...inputOptions(4)}
        />
        <TextInput source="buyers" validate={[required()]} {...inputOptions(4)} />
      </SimpleForm>
    </Edit>
  );
};

export default UserEdit;
