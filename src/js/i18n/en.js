import englishMessages from "jazasoft/lib/i18n/default-messages/js-language-english";

// const englishMessages = i18n["js-language-english"];

export default {
  ...englishMessages,
  app_name_short: "ZHOMEO",
  app_name_full: "ZILANIHOMEO",
  dashboard: "Dashboard",
  forbidden: {
    title: "Forbidden",
    message: "Access Denied. You do not have enough privilege for this operation"
  },
  resources: {
    users: {
      name: "User |||| Users",
      fields: {
        fullName: "Full Name",
        username: "Username",
        email: "Email",
        mobile: "Mobile",
        roles: "Roles"
      }
    },

    visitCalendar: {
      name: "Calendar |||| Calendar"
    },

    // library tabs
    businessUnits: {
      name: "Business Unit |||| Business Unit",
      fields: {
        name: "Business Unit",
        location: "Location"
      }
    },
    awards: {
      name: "Award |||| Award",
      fields: {
        name: "Award",
        desc: "Description",
        directNomination: "Direct Nomination"
      }
    },
    committees: {
      name: "Committee |||| Committee",
      fields: {
        type: "Committee Type",
        year: "Year",
        "award.name": "Award"
      }
    },
    bestPractices: {
      name: "Best Practices ||||| Best Practices"
    },

    performanceGrid: {
      name: "Performance Grid ||||| Performance Grid"
    }
  }
};
