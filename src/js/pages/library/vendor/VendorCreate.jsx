import React from "react";
import { Create, SimpleForm, TextInput } from "jazasoft";

import { fieldOptions } from "../../../utils/helpers";

export const VendorCreate = (props) => {
  return (
    <Create {...props}>
      <SimpleForm redirect="home">
        <TextInput source="name" {...fieldOptions(4)} />
        <TextInput source="description" {...fieldOptions(4)} />
      </SimpleForm>
    </Create>
  );
};
export default VendorCreate;
