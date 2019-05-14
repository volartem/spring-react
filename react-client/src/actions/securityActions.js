import axios from "axios";
import {GET_ERRORS, SET_CURRENT_USER} from "./types";
import setJWToken from "../securityUtils/setJWToken";
import jwt_decode from "jwt-decode";

export const createNewUser = (newUser, history) => async dispatch => {
    try {
        await axios.post("/api/users/register", newUser);
        history.push("/login");
        dispatch({
            type: GET_ERRORS,
            payload: {}
        });
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};


export const login = LoginRequest => async dispatch => {
    try {

        const res = await axios.post("/api/users/login", LoginRequest);
        const {token} = res.data;
        localStorage.setItem("jwtToken", token);
        setJWToken(token);
        const decoded = jwt_decode(token);
        dispatch({
            type: SET_CURRENT_USER,
            payload: decoded
        });
    } catch (err) {
        dispatch({
            type: GET_ERRORS,
            payload: err.response.data
        });
    }
};
