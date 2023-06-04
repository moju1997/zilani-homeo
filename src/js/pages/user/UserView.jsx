import React from "react";

import { Roles } from "../../utils/types";
import { Show, SimpleShowLayout, TextField, FunctionField } from "jazasoft";

const fieldOptions = {
  xs: 12,
  sm: 4
};

const UserView = props => {
  return (
    <Show {...props}>
      <SimpleShowLayout style={{ paddingTop: "1em" }}>
        <TextField label="Full Name" source="fullName" {...fieldOptions} />
        <TextField label="Username" source="username" {...fieldOptions} />
        <TextField label="Email" source="email" {...fieldOptions} />
        <TextField label="Mobile" source="mobile" {...fieldOptions} />
        <FunctionField
          label="Role"
          {...fieldOptions}
          render={record => Roles[Object.keys(Roles).find(key => record?.roles === Roles[key].id)]?.name || ""}
        />
        <TextField label="Mobile" source="mobile" {...fieldOptions} />
      </SimpleShowLayout>
    </Show>
  );
};

export default UserView;
