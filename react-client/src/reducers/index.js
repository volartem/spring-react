import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import projectReducer from "./projectReducer";
import securityReducer from "./securityReducer";


export default combineReducers({
    errors: errorReducer,
    all: projectReducer,
    security: securityReducer,
});