import { combineReducers } from 'redux'
import AuthReducer from "./auth"
const allReducer = combineReducers({
    AReducer: AuthReducer,
    
});

export default allReducer;