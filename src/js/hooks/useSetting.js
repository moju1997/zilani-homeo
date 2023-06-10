import { isEmpty } from "../utils/helpers";
import SettingKeys from "../utils/SettingKeys";
import useQuery from "../utils/useQuery";

const useSetting = (key, resource) => {
  const { data: settingList = [] } = useQuery(
    key && {
      url: "settings",
      params: { search: `key==${key}${isEmpty(resource) ? "" : `;resource==${resource}`}` },
    }
  );
  let result = settingList[0]?.value;
  if (key === SettingKeys.ENTITY_FIELDS) {
    result = result?.split(",").map(e => e.trim()) || [];
  }
  if (key === SettingKeys.ENTITY_FIELD_VALIDATION) {
    result = !isEmpty(result) ? JSON.parse(result || "{}") : {};
  }

  if (key === SettingKeys.ENTITY_FIELDS) {
    return result || [];
  }
  return result;
};

export default useSetting;
