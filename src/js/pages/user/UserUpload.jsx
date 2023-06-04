import React from "react";
import { useDispatch } from "react-redux";

//material ui
import makeStyles from "@material-ui/core/styles/makeStyles";
import Paper from "@material-ui/core/Paper";

//jazasoft
import { PageHeader, SimpleForm, showNotification, FileInput, FileField, required } from "jazasoft";

// read excel file
import readXlsxFile from "read-excel-file";

// local
import ExcelErrorDialog from "../../components/ExcelErrorDialog";

//helper
import { REGEX_USERNAME, REGEX_EMAIL } from "../../utils/regex";
import { Role } from "../../utils/types";
import useQuery from "../../utils/useQuery";
import useMutation from "../../utils/useMutation";
import hasRole from "../../utils/hasRole";

const useStyles = makeStyles({
  root: {
    margin: "1.5em",
    padding: "1.5em"
  }
});

const UserUpload = props => {
  const classes = useStyles();
  const dispatch = useDispatch();

  const [errors, setErrors] = React.useState();

  const { data: businessUnitList = [] } = useQuery({
    url: `businessUnits`,
    params: { size: 100 }
  });

  const [mutate] = useMutation();

  const onSubmit = async values => {
    const { file } = values;

    try {
      let { rows, errors } = await readXlsxFile(file.rawFile, {
        schema: getSchema()
      });

      if (errors && errors.length > 0) {
        const errorList = errors.map(({ error, row, column }) => ({
          row,
          column,
          message: error
        }));
        setErrors(errorList);
        return;
      }

      rows = rows.map(row => {
        const bu = businessUnitList.find(b => b.name?.toLowerCase().trim() === row.businessUnit?.toLowerCase().trim());
        return {
          ...row,
          roles: Role[row.roles?.trim()],
          businessUnitId: bu?.id,
          businessUnit: null
        };
      });

      //validate
      errors = [];
      rows.forEach((row, idx) => {
        if (!row.fullName) {
          errors.push({
            row: idx + 1,
            column: "FULL NAME",
            message: "Full Name required."
          });
        }
        if (!row.username) {
          errors.push({
            row: idx + 1,
            column: "USERNAME",
            message: "Username required."
          });
        }
        if (!row.mobile) {
          errors.push({
            row: idx + 1,
            column: "MOBILE",
            message: "Mobile number required."
          });
        }
        if (hasRole([row.roles], [Role.BULeader]) && !row.businessUnitId) {
          errors.push({
            row: idx + 1,
            column: "BUSINESS UNIT",
            message: "Business Unit required."
          });
        }
        if (!row.roles) {
          errors.push({
            row: idx + 1,
            column: "ROLE",
            message: "Role required."
          });
        }
      });

      if (errors.length > 0) {
        setErrors(errors);
        return;
      }

      let options = {
        url: `users/batchSave`,
        method: "post",
        data: rows
      };
      mutate(options, {
        onSuccess: () => {
          dispatch(showNotification("Users uploaded successfully."));
          props.history.push("/users");
        }
      });
    } catch (err) {
      console.log({ err });
    }
  };

  const getSchema = rows => {
    return {
      USERNAME: {
        prop: "username",
        type: String,
        validate: validate({ column: "username" })
      },
      "FULL NAME": {
        prop: "fullName",
        type: String
      },
      EMAIL: {
        prop: "email",
        type: String,
        validate: validate({ column: "email" })
      },
      MOBILE: {
        prop: "mobile",
        type: String
      },
      ROLE: {
        prop: "roles",
        type: String,
        validate: validate({ column: "roles" })
      }
    };
  };

  const validate = ({ column }) => value => {
    value = value && String(value);
    if (column === "username") {
      if (!REGEX_USERNAME.test(value.trim())) {
        throw new Error(
          `Invalid value for Username. Username should consists of Alphabets, numbers and (hyphen, underscore, dot) special characters only and starting with Alphabet`
        );
      }
    } else if (column === "email") {
      if (!REGEX_EMAIL.test(value.trim())) {
        throw new Error(`Invalid value for Email.`);
      }
    } else if (column === "roles") {
      if (!Role[value?.trim()]) {
        throw new Error(
          `Invalid Role - ${value}
          }. Accepted roles are: ${Object.keys(Role).join(", ")}`
        );
      }
    }
  };

  return (
    <div>
      <PageHeader title="Upload Users" />

      <ExcelErrorDialog maxWidth="xl" open={Array.isArray(errors)} errors={errors} onClose={() => setErrors(null)} />

      <Paper className={classes.root}>
        <SimpleForm onSubmit={onSubmit}>
          <FileInput
            source="file"
            label="Excel file"
            accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
            xs={12}
            fullWidth
            validate={required()}>
            <FileField source="src" title="title" />
          </FileInput>
        </SimpleForm>
      </Paper>
    </div>
  );
};

export default UserUpload;
