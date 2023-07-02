import React from "react";
import ReactEcharts from "echarts-for-react";

// material-ui
// import makeStyles from "@material-ui/core/styles/makeStyles";

// perfect scrollbar

import "react-perfect-scrollbar/dist/css/styles.css";

// jaza-soft
import {  translate} from "jazasoft";


// Register the required components

// const useStyles = makeStyles({
//   content: {
//     marginLeft: "1em",
//     marginRight: "1.5em",
//   },
//   card: {
//     height: "100%",
//   },
//   cardHeader: {
//     display: "flex",
//     justifyContent: "space-between",
//     alignItems: "center",
//   },
//   tile: {
//     width: "100%",
//     boxShadow: "rgba(0, 0, 0, 0.15) 0px 5px 15px",
//     padding: "1em 1.5em",
//     display: "flex",
//     flexDirection: "column",
//     justifyContent: "space-between",
//     height: 135,
//   },
// });

const Dashboard = props => {

    const option = {
        xAxis: {
          type: 'category',
          data: ['Dilution 100ml', 'Patient', 'Utility', 'Dilution 450ml', 'Syrup', 'Vitamins', 'Suppliments']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar'
          }
        ]
      };
  
  return (
    <div>
        <ReactEcharts option={option} />
    </div>
  );
};

export default translate(Dashboard);
