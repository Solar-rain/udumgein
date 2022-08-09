import request from "@/utils/request";

export function Login(data) {
    return request({
        url: '/login',
        method: 'post',
        data
    })
}

export function sendCode(email) {
    return request({
        url: '/login/' + email,
        method: 'post'
    })
}