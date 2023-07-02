import englishMessages from "jazasoft/lib/i18n/default-messages/js-language-english";

// const englishMessages = i18n["js-language-english"];

export default {
  ...englishMessages,
  app_name_short: "ZHOMEO",
  app_name_full: "ZILANIHOMEO",
  dashboard: "Dashboard",
  forbidden: {
    title: "Forbidden",
    message: "Access Denied. You do not have enough privilege for this operation",
  },
  resources: {
    users: {
      name: "User |||| Users",
      fields: {
        fullName: "Full Name",
        username: "Username",
        email: "Email",
        mobile: "Mobile",
        roles: "Roles",
      },
    },

    visitCalendar: {
      name: "Calendar |||| Calendar",
    },

    // library tabs
    medicineCategories: {
      name: "Medicine Category |||| Medicine Category",
      fields: {
        name: "Medicine Category",
      },
    },
    medicinePowers: {
      name: "Medicine Power |||| Medicine Power",
      fields: {
        name: "Medicine Power",
      },
    },
    medicineBrands: {
      name: "Medicine Brand |||| Medicine Brand",
    },
    medicineQuantities: {
      name: "Medicine Quantity ||||| Medicine Quantity",
    },

    performanceGrid: {
      name: "Performance Grid ||||| Performance Grid",
    },
  },
};
