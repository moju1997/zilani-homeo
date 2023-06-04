import React from "react";
import { useSafeSetState } from "./hooks";
import { RestMethods, showNotification, FETCH_START, FETCH_END } from "jazasoft";
import { dataProvider } from "../App";
import { useDispatch } from "react-redux";
import { push } from "connected-react-router";
/**
 * {
 *      url:
 *      method:
 *      params:
 *      data:
 *      headers:
 * }
 * @param {Object} options axiosOptions
 * @param {Function} mapper Mapper function to transform payload returned from API. It should be pure function, not dynamic
 */
const useQuery = (options, mapper) => {
  const [state, setState] = useSafeSetState({ loading: false, data: undefined, error: null });
  const dispatch = useDispatch();

  /* eslint-disable react-hooks/exhaustive-deps */
  React.useEffect(() => {
    const fetchAsync = async () => {
      setState(prevState => ({ ...prevState, loading: true }));
      dispatch({ type: FETCH_START });
      try {
        const response = await dataProvider(RestMethods.CUSTOM, null, {
          ...options,
          url: `${options.url}`,
          method: options.method || "get"
        });
        let data = response.data && response.data.content ? response.data.content : response.data;
        if (mapper) {
          if (Array.isArray(data)) {
            data = data.map(mapper);
          } else {
            data = mapper(data);
          }
        }
        setState({ loading: false, data, error: null });
        dispatch({ type: FETCH_END });
      } catch (e) {
        console.log(e);
        setState({ loading: false, data: undefined, error: e });
        dispatch({ type: FETCH_END });
        if (typeof e.message === "string") {
          dispatch(showNotification(e.message, { type: "danger" }));
        }
        if (e.status === 401) {
          dispatch(push("/login"));
        }
      }
    };
    if (options && Object.keys(options).length > 0) {
      fetchAsync();
    }
  }, [JSON.stringify(options), setState]);
  /* eslint-enable react-hooks/exhaustive-deps */

  return state;
};

export default useQuery;
