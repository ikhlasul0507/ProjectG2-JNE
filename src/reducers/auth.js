let defaultState = {
    isLogin: false,
    userLogin: {
        idUser:"",
        username : "",
        email : "",
        password : "",
        email : ""
    }
}

// combineReducer
const authReducer = (state = defaultState, action) => {
    console.warn("state:", state);
    console.warn("action:", action);
    switch (action.type) {
        case "LOGIN_SUCCESS":
            // console.log(action.payload.username)
            return {
                isLogin: true,
                userLogin: {
                    idUser : action.payload.userData.idUser,
                    username: action.payload.userData.username,
                    email: action.payload.userData.email,
                    password: action.payload.userData.password
                }
            }

        case "LOGOUT_SUCCESS":
            return defaultState
        // @@@INIT
        default:
            return state
    }
    

}
export default authReducer