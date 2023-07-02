/* eslint-disable react-hooks/exhaustive-deps */
import React from "react";

import CardContent from "@material-ui/core/CardContent";

import { SimpleShowLayout, PageHeader, TextField, PageFooter, BackButton, EditButton } from "jazasoft";

// material-ui
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";

// helper
import useQuery from "../../../utils/useQuery";

const Footer = ({ classes, basePath, resource, roles, hasAccess }) => (
  <PageFooter className={classes.pageFooter}>
    <BackButton variant="contained" basePath={basePath} resource={resource} />

    <EditButton variant="contained" color="primary" basePath={basePath} resource={resource} to="edit" />
  </PageFooter>
);

const useStyles = makeStyles(() => ({
  pageFooter: {
    "& > *": {
      marginRight: "1em",
    },
  },
  marginVertical: {
    margin: "1.5em 1.5em",
  },
}));

const VendorView = (props) => {
  const classes = useStyles();
  const { id, i18nKey, resource, basePath, hasAccess, roles } = props;
  const { data: buyer } = useQuery({ url: `vendors/${id}` });

  return (
    <div className={classes.root}>
      <PageHeader title="resources.vendors.pageHeader.view" titleArgs={{ _: "View Vendor" }} />
      <div>
        <Card className={classes.marginVertical}>
          <CardContent>
            <SimpleShowLayout
              style={{ paddingTop: "1em" }}
              record={buyer}
              i18nKey={i18nKey}
              basePath={basePath}
              resource={resource}
              footer={<Footer classes={classes} hasAccess={hasAccess} roles={roles} />}
            >
              <TextField source="name" />
              <TextField source="description" />
            </SimpleShowLayout>
          </CardContent>
        </Card>
      </div>
    </div>
  );
};

export default VendorView;
