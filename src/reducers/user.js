let defaultState = {
    users: []
}
// combineReducer
const userReducer = (state = defaultState, action) => {
    switch (action.type) {
        case "SET_DATA":
            return {
                users: action.payload.users,
            }

        case "CLEAR_DATA":
            return defaultState

        default:
            return state
    }

}

export default userReducer