import React from "react";
import makeStyles from "@material-ui/styles/makeStyles";
import { useState } from "react";
import {
  List,
  TextField,
  Datagrid,
  CreateButton,
  EditButton,
  DeleteButton,
  Button,
  translate,
  Filter,
  FilterButton,
  SelectInput,
} from "jazasoft";


import UploadIcon from "mdi-material-ui/Upload";
import DownloadIcon from "mdi-material-ui/Download";
import useMutation from "../../../utils/useMutation";

const Actions = ({ basePath, onDownload, history, busy, translate }) => (
  <div>
    <CreateButton basePath={basePath} showLabel={false} />
    <Button
      showLabel={false}
      label={translate(`js.action.upload`, { _: "Upload" })}
      onClick={() => history.push("/seasons/batchSave")}
    >
      <UploadIcon />
    </Button>
    <Button showLabel={false} label={translate("js.action.download")} disabled={busy} onClick={onDownload}>
      <DownloadIcon />
    </Button>
    <FilterButton showLabel={false} />
  </div>
);

const useHomeStyles = makeStyles((theme) => ({
  buttonEdit: {
    width: theme.spacing(14),
  },
  buttonDelete: {
    width: theme.spacing(16),
  },
}));

export const VendorHome = (props) => {
  const classes = useHomeStyles();
  const [busy, setBusy] = useState(false);
  const [download] = useMutation({ rawResponse: true });

  const onDownload = () => {
    setBusy(true);
    const options = {
      url: `seasons`,
      method: "get",
      headers: {
        Accept: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
      },
      responseType: "arraybuffer",
    };
    download(options, {
      onSuccess: (response) => {
        if (response.status === 200) {
          const header = response.headers["content-disposition"];
          var filename = header ? header.match(/filename="(.+)"/)[1] : `SEASON.xlsx`;
          let blob = new Blob([response.data], {
              type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            }),
            link = document.createElement("a");
          link.href = window.URL.createObjectURL(blob);
          link.download = filename;
          link.click();
        }
      },
    });
    setBusy(false);
  };
  const filters = (
    <Filter>
      <SelectInput
        label="Status"
        source="enabled"
        allowEmpty
        choices={[
          { id: "true", name: "Active" },
          { id: "false", name: "Not Active" },
        ]}
        xs={12}
        fullWidth={true}
      />
    </Filter>
  );
  return (
    <List
      filters={filters}
      actions={<Actions history={props.history} onDownload={!busy && onDownload} translate={translate} />}
      {...props}
    >
      <Datagrid>
        <TextField source="name" />
        <TextField source="description" />

        <EditButton cellClassName={classes.buttonEdit} />

        <DeleteButton cellClassName={classes.buttonDelete} />
      </Datagrid>
    </List>
  );
};

export default VendorHome;
