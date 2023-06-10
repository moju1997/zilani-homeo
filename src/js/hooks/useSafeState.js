import * as React from "react";
import isEqual from "fast-deep-equal/react";

export default function useSafeState(initialState) {
  const [state, setState] = React.useState(initialState);

  const mountedRef = React.useRef(false);
  React.useEffect(() => {
    mountedRef.current = true;
    return () => (mountedRef.current = false);
  }, []);

  const safeSetState = React.useCallback(
    args => {
      if (mountedRef.current) {
        setState(prevState => {
          const newState = typeof args === "function" ? args(prevState) : args;
          return isEqual(prevState, newState) ? prevState : newState;
        });
      }
    },
    [mountedRef, setState]
  );

  return [state, safeSetState];
}
