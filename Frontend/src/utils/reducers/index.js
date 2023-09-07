import lanReducer from './lan.js';
import { combineReducers } from 'redux';
const rootReducer = combineReducers({
    Language: lanReducer,
});

export default rootReducer;
