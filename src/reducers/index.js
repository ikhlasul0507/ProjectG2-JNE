import {createStore, combineReducers} from 'redux'
import AuthReducer from "./auth"
let store = createStore(combineReducers({
    AReducer: AuthReducer,
    
}))

export default store