import React from "react";
import useSafeSetState from "./useSafeState";
import { RestMethods, showNotification, FETCH_START, FETCH_END, SAVING_START, SAVING_END } from "jazasoft";
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
 * @param {Object} sideEffects sideEffects - {onSuccess: (data) => {}, onFailure: (error) => {}}
 * @param {Function} mapper Mapper function to transform payload returned from API. It should be pure function, not dynamic
 */
const useMutation = (arg1, sideEffects = {}, mapper) => {
  const { rawResponse = false, autoHideDuration, ...options } = arg1 || {};
  const [state, setState] = useSafeSetState({ loading: false, data: undefined, error: null });
  const dispatch = useDispatch();

  /* eslint-disable react-hooks/exhaustive-deps */
  const mutate = React.useCallback(
    async (callTimeOptions = {}, callTimeSideEffects = {}) => {
      const method = callTimeOptions.method || options.method;
      const updateSaving = method && method.toLowerCase() !== "get";

      setState(prevState => ({ ...prevState, loading: true }));
      dispatch({ type: FETCH_START });
      if (updateSaving) {
        dispatch({ type: SAVING_START });
      }

      //Merge CallTimeOptions and Definition Options
      const mergedOptions = {
        ...options,
        ...callTimeOptions,
        url: `${callTimeOptions.url || options.url}`,
        method,
        params: {
          ...options.params,
          ...callTimeOptions.params,
        },
        data: options.data || callTimeOptions.data,
      };

      try {
        const response = await dataProvider(RestMethods.CUSTOM, null, mergedOptions);
        const onSuccess = callTimeSideEffects.onSuccess || sideEffects.onSuccess;
        if (rawResponse) {
          onSuccess && onSuccess(response);
          setState({ loading: false, data: response, error: null });
          dispatch({ type: FETCH_END });
          if (updateSaving) {
            dispatch({ type: SAVING_END });
          }
        } else {
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
          if (updateSaving) {
            dispatch({ type: SAVING_END });
          }
          onSuccess && onSuccess(data);
        }
      } catch (e) {
        console.log(e);
        setState({ loading: false, data: undefined, error: e });
        dispatch({ type: FETCH_END });
        if (updateSaving) {
          dispatch({ type: SAVING_END });
        }
        const onFailure = callTimeSideEffects.onFailure || sideEffects.onFailure;
        onFailure && onFailure(e);
        if (typeof e.message === "string") {
          dispatch(showNotification(e.message, { type: "danger", autoHideDuration }));
        }
        if (e.status === 401) {
          dispatch(push("/login"));
        }
      }
    },
    [JSON.stringify(options), setState]
  );
  /* eslint-enable react-hooks/exhaustive-deps */

  return [mutate, state];
};

export default useMutation;
