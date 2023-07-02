// import React from "react";
// import { useDispatch, useSelector } from "react-redux";
// //material ui
// import makeStyles from "@material-ui/styles/makeStyles";
// import Paper from "@material-ui/core/Paper";
// //jazasoft
// import { PageHeader, SimpleForm, required, translate, showNotification } from "jazasoft";

// // local
// import ExcelErrorDialog from "../../../components/ExcelErrorDialog";
// import MyFileInput from "../../../components/MyFileInput";
// import useSafeState from "../../../hooks/useSafeState";
// import useMutation from "../../../utils/useMutation";
// import hasSuperPrivilege from "../../../utils/hasSuperPrivilege";
// import { ResourceId, Scope } from "../../../utils/privilege"
// import Forbidden from "../../../components/Forbidden";

// const mapper = ({ serialNo, field, message }, i18nMap) => {
//   const orderMap = i18nMap?.resources?.activities?.fields || {};
//   let column = orderMap[field] || field;
//   if (typeof column === "object") {
//     column = column.name;
//   }
//   return {
//     row: serialNo,
//     column,
//     message: Array.isArray(message) ? message.join(", ") : message,
//   };
// };


// const useStyles = makeStyles(_ => ({
//   root: {
//     margin: "1.5em",
//     padding: "1.5em",
//   },
// }));

// const VendorUpload = props => {
//   const { translate, history, roles = [], hasAccess = () => false } = props;

//   const [errorList, setErrorList] = useSafeState([]);

//   const i18nMap = useSelector(state => state.i18n?.messages || {});

//   const [mutate] = useMutation({ autoHideDuration: 15000 });
//   const dispatch = useDispatch();

//   const onUpload = values => {
//     const { file } = values;
//     var data = new FormData();
//     data.append("file", file && file.rawFile);
//     const options = {
//       url: `seasons/batchSave`,
//       method: "post",
//       data: data,

//       headers: { "Content-Type": "multipart/form-data" },
//     };

//     mutate(options, {
//       onSuccess: () => {
//         dispatch(showNotification(translate("resources.seasons.success.upload", { _: "seasons uploaded successfully" })));
//         history.push("/library/seasons");
//       },
//       onFailure: err => {
//         if (err.status === 400 && err.errors && Array.isArray(err.errors)) {
//           setErrorList(err.errors?.map(e => mapper(e, i18nMap)));
//         }
//       },
//     });
//   };
//   const classes = useStyles();

//   if (!hasSuperPrivilege(roles, hasAccess, ResourceId.SEASON, Scope.UPDATE, "default")) {
//     return <Forbidden history={history} />;
//   }

//   return (
//     <div>
//       <PageHeader title="resources.seasons.page.upload" titleArgs={{ _: "Vendor Master Bulk Upload" }} />

//       <ExcelErrorDialog
//         maxWidth="xl"
//         open={errorList?.length > 0 || false}
//         errors={errorList}
//         onClose={() => setErrorList(null)}
//       />

//       <Paper className={classes.root}>
//         <SimpleForm onSubmit={onUpload}>
//           <MyFileInput
//             source="file"
//             label={translate("resources.seasons.title.excel_file", { _: "Excel file" })}
//             accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
//             message={translate("js.message.drop_file", { _: "Drag and drop file here, or click to select file" })}
//             xs={12}
//             fullWidth
//             validate={required()}
//           />
//         </SimpleForm>
//       </Paper>
//     </div>
//   );
// };

// export default translate(VendorUpload);
