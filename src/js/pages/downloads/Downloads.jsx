import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Paper from "@material-ui/core/Paper";
import DownloadsIcon from "@material-ui/icons/GetApp";
import IconButton from "@material-ui/core/IconButton";
import Tooltip from "@material-ui/core/Tooltip";

import { PageHeader } from "jazasoft";

import Table from "jazasoft/lib/mui/components/Table";
import useMutation from "../../utils/useMutation";

const useStyles = makeStyles(theme => ({
  root: {
    margin: theme.spacing(3)
  },
  headerCell: {
    padding: "18px 12px",
    fontSize: 14
  },
  rowCell: {
    fontSize: 14
  }
}));

const ActionItem = ({ tooltip, icon, busy, onClick }) => (
  <Tooltip title={tooltip} onClick={onClick}>
    <IconButton disabled={busy} aria-label={tooltip}>
      {icon}
    </IconButton>
  </Tooltip>
);

const Downloads = () => {
  const classes = useStyles();

  const [mutate, { loading: busy }] = useMutation({ rawResponse: true });

  const onDownload = name => () => {
    const options = {
      url: `templates`,
      method: "get",
      params: { name },
      responseType: "arraybuffer"
    };
    mutate(options, {
      onSuccess: response => {
        const header = response.headers["content-disposition"];
        var filename = header ? header.match(/filename="(.+)"/)[1] : `${name}.xlsx`;

        let blob = new Blob([response.data], {
          type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        });
        let link = document.createElement("a");
        link.href = window.URL.createObjectURL(blob);
        link.download = filename;
        link.click();
      }
    });
  };

  const columns = [
    { title: "Name", dataKey: "name" },
    { title: "", dataKey: "action", align: "right" }
  ];

  const rows = [
    {
      name: "User Upload Template",
      action: (
        <ActionItem
          tooltip="Download"
          icon={<DownloadsIcon />}
          busy={busy}
          onClick={onDownload("USER_UPLOAD_TEMPLATE")}
        />
      )
    }
  ];

  return (
    <div>
      <PageHeader title="Downloads" />

      <Paper className={classes.root}>
        <Table classes={{ headerCell: classes.headerCell, rowCell: classes.rowCell }} columns={columns} rows={rows} />
      </Paper>
    </div>
  );
};

export default Downloads;
