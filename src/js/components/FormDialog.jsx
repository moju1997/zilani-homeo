import React from "react";

import Dialog from "@material-ui/core/Dialog";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
import Divider from "@material-ui/core/Divider";

import CancelIcon from "@material-ui/icons/Cancel";

import { PageFooter, SaveButton, SimpleForm, Button } from "jazasoft";

const FormDialog = ({
  id,
  title,
  open,
  maxWidth,
  fullWidth = true,
  record = {},
  onClose,
  onSubmit,
  dialogProps = {},
  formProps = {},
  children
}) => {
  return (
    <Dialog
      id={id || "form-dialog-container"}
      fullWidth={fullWidth}
      maxWidth={maxWidth}
      open={open}
      onClose={onClose}
      {...dialogProps}>
      <DialogTitle id="form-dialog-title">{title}</DialogTitle>
      <Divider />
      <DialogContent id="form-dialog-content">
        <SimpleForm
          record={record}
          onSubmit={onSubmit}
          footer={props => (
            <PageFooter style={{ marginLeft: 0 }}>
              <SaveButton {...props} label="Submit" style={{ marginLeft: 0 }} />
              <Button label="Cancel" onClick={onClose} variant="outlined" color="primary">
                <CancelIcon />
              </Button>
            </PageFooter>
          )}
          {...formProps}>
          {children}
        </SimpleForm>
      </DialogContent>
    </Dialog>
  );
};

export default FormDialog;
