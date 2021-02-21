let defaultState = {
    isLogin: false,
}

const authReducer = (state= defaultState, action)=>{
    switch(action.type){
        case "LOGIN_SUCCESS":
        return{
            isLogin : true,
        }
        default:
            return state
    }
}

export default authReducer;