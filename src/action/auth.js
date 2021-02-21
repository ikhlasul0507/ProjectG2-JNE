const authFn = payload =>{
    return { type: "LOGIN_SUCCESS", payload: payload.data}
}
export default authFn