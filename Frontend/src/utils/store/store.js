const { createStore } = require('redux');
const { default: rootReducer } = require('~/utils/reducers/index.js');

const store = createStore(rootReducer);
export default store;
