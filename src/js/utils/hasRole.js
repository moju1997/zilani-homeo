/**
 * Check Logged in user have specific roles.
 *
 * Usage - hasRole(roles, [Role.ADMIN_USER, Role.MERCHANT]) // to check whether current user is Admin or Merchant
 *
 * @param {String[]} currRoles Roles of Logged in user
 * @param {String[]} roles Roles to check
 */
const hasRole = (currRoles = [], roles = []) => currRoles.filter(role => roles.includes(role)).length > 0;

export default hasRole;
