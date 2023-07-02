import * as React from "react";
import { Route } from "react-router-dom";
import { createBrowserHistory } from "history";
import axios from "axios";

import avatar from "./asset/img/avatar-male.png";
import logoDark from "./asset/img/logo-dark.png";
import logoWhite from "./asset/img/logo-white.png";

// import Dashboard from "./pages/dashboard/Dashboard";
import Dashboard from "./pages/dashboard/Dashboard";

//icons
import MenuIcon from "@material-ui/icons/Menu";
import UserIcon from "mdi-material-ui/AccountGroup";
import DownloadsIcon from "@material-ui/icons/GetApp";

import { App as JApp, Resource, createAuthProvider, createDataProvider } from "jazasoft";

import englishMessage from "./i18n/en";
import theme from "./theme";

// pages
import { VendorHome, CreateVendor, EditVendor } from "./pages/library/Vendor";
import Downloads from "./pages/downloads/Downloads";

import Profile from "./pages/profile/Profile";

import { UserHome, UserCreate, UserEdit, UserView, UserUpload } from "./pages/user";
import { CreateMedicineCategory, EditMedicineCategory, MedicineCategoryHome } from "./pages/library/MedicineCategory";
import { CreateMedicinePower, EditMedicinePower, MedicinePowerHome } from "./pages/library/MedicinePower";
import { CreateMedicineBrand, EditMedicineBrand, MedicineBrandHome } from "./pages/library/MedicineBrand";
import { CreateMedicineQuantity, EditMedicineQuantity, MedicineQuantityHome } from "./pages/library/MedicineQuantity";

// const rootUrl = window.location.protocol + "//" + window.location.hostname + (window.location.port ? ":" + window.location.port : "");
const rootUrl = `http://${window.location.hostname}:8018`;
// const rootUrl = "http://costing-dev.jaza-soft.com";

const appId = "zilani";
const authServerUrl = `${rootUrl}`;
const appUrl = `${rootUrl}/api`;

export const authProvider = createAuthProvider(authServerUrl, "Basic Y2xpZW50OnNlY3JldA==", appId);

export const dataProvider = createDataProvider(appUrl, appId);

(function () {
  axios
    .get(`${rootUrl}/buildInfo`)
    .then((response) => {
      localStorage.setItem(`${appId}-build-info`, JSON.stringify(response.data));
    })
    .catch((err) => {
      console.log(err);
    });
})();

const i18nProvider = (locale) => {
  return englishMessage;
};

const history = createBrowserHistory();

const initialState = {
  jazasoft: {
    ui: {
      sidebarOpen: false,
      filterOpen: false,
      optimistic: false,
      viewVersion: 0,
    },
  },
};

const resources = [
  { i18nKey: "vendors", resource: "vendors" },
  { i18nKey: "seasons", resource: "seasons" },
  { i18nKey: "users", resource: "users" },
];

const customRoutes = [
  <Route name="profile" exact path="/profile" component={Profile} />,
  <Route name="users" resource="users" exact path="/users/upload" component={UserUpload} />,
];

class App extends React.Component {
  render() {
    return (
      <JApp
        appId={appId}
        appName="ZILANI"
        appNameShort="zilani"
        authProvider={authProvider}
        dataProvider={dataProvider}
        i18nProvider={i18nProvider}
        resources={resources}
        initialState={initialState}
        customRoutes={customRoutes}
        logoWhite={logoWhite}
        logoDark={logoDark}
        avatar={avatar}
        dashboard={Dashboard}
        history={history}
        theme={theme}
      >
        {() => {
          let resourceList = [];

          resourceList.push(
            <Resource
              name="users"
              resource="users"
              home={UserHome}
              create={UserCreate}
              edit={UserEdit}
              view={UserView}
              icon={UserIcon}
            />
          );

          resourceList.push(
            <Resource name="library" icon={MenuIcon}>
              <Resource
                name="vendors"
                resource="vendors"
                home={VendorHome}
                create={CreateVendor}
                edit={EditVendor}
                icon={MenuIcon}
              />
              <Resource
                name="medicineCategories"
                resource="medicineCategories"
                home={MedicineCategoryHome}
                create={CreateMedicineCategory}
                edit={EditMedicineCategory}
                icon={MenuIcon}
              />
              <Resource
                name="medicinePowers"
                resource="medicinePowers"
                home={MedicinePowerHome}
                create={CreateMedicinePower}
                edit={EditMedicinePower}
                icon={MenuIcon}
              />
              <Resource
                name="medicineBrands"
                resource="medicineBrands"
                home={MedicineBrandHome}
                create={CreateMedicineBrand}
                edit={EditMedicineBrand}
                icon={MenuIcon}
              />
              <Resource
                name="medicineQuantities"
                resource="medicineQuantities"
                home={MedicineQuantityHome}
                create={CreateMedicineQuantity}
                edit={EditMedicineQuantity}
                icon={MenuIcon}
              />
            </Resource>
          );

          resourceList.push(<Resource name="downloads" home={Downloads} icon={DownloadsIcon} />);

          return resourceList;
        }}
      </JApp>
    );
  }
}

export default App;
