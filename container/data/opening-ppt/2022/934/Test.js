const userList = [
  { name: "Mojahid", age: 19 },
  { name: "Shahid", age: 20 },
  { name: "Arshad", age: 18 },
];

const ageSum = userList.reduce((acc, user) => acc + user.age, 0);

const avgAge = ageSum / userList.length;

const names = userList.map((e) => e.name);

const filterUser = userList.filter((e) => e.age > 19);

console.log(userList);
console.log(ageSum);
console.log(avgAge);
console.log(filterUser);


// What is Diff between Let const and Var
//Let and Cost are Block Scope - Wrong
// Var is Function Scope - Wrong


//2.Diif between Null and Undefined 
// let age; -- undefined -When a variable is declared but not assigned, it has the value of undefined and it’s type is also undefined.
// let age = null;=It represents a non-existent or a invalid value.
// Null: object, undefined: undefined

//3. Difference between “ == “ and “ === “ operators. Both are comparison operators. The difference between both the operators is that “==” is used to compare values whereas, “ === “ is used to compare both values and types.

//Explain Closures in JavaScript - Closures are an ability of a function to remember the variables and functions that are declared in its outer scope.

// React:
// state & props
// functional/class component
// hooks - useState, useEffect and when it is used.

//


