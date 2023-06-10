import * as React from "react";
import axios from "axios";
import { zonedStartOfDay, zonedEndOfDay } from "../utils/dateUtils";

import useSafeState from "./useSafeState";
import { rootUrl, appId } from "../App";

const useTime = version => {
  const [state, setState] = useSafeState({ timeNow: null, startOfDay: null, endOfDay: null });

  React.useEffect(() => {
    const fetchTimeAsync = async () => {
      const accessToken = localStorage.getItem(`access_token_${appId}`);

      try {
        const response = await axios.get(`${rootUrl}/time`, { headers: { Authorization: `Bearer ${accessToken}` } });

        if (response.data && response.data.datetime) {
          const data = response.data;
          const timeNow = Date.parse(data.datetime);
          const startOfDay = zonedStartOfDay(data.utc_datetime, data.utc_offset).getTime();
          const endOfDay = zonedEndOfDay(data.utc_datetime, data.utc_offset).getTime();
          setState({ ...data, timeNow, startOfDay, endOfDay });
        }
      } catch (error) {
        console.log(error);
      }
    };
    fetchTimeAsync();
  }, [version, setState]);

  return state;
};

export default useTime;
