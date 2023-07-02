import React from "react";
import { useSelector } from "react-redux";
import { Edit, SimpleForm, TextInput } from "jazasoft";

import { fieldOptions } from "../../../utils/helpers";

export const VendorEdit = (props) => {
  const { resource } = props;
  const record = useSelector(
    (state) =>
      state.jazasoft.resources[resource] &&
      state.jazasoft.resources[resource].data &&
      state.jazasoft.resources[resource].data[props.id]
  );

  return (
    <Edit {...props}>
      <SimpleForm>
        <TextInput
          source="name"
          options={{ fullWidth: true, disabled: record && record.isReferenced }}
          {...fieldOptions(4)}
        />

        <TextInput source="description" {...fieldOptions(4)} />
      </SimpleForm>
    </Edit>
  );
};

export default VendorEdit;
