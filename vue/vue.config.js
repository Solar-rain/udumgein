const {defineConfig} = require('@vue/cli-service')
module.exports = defineConfig({
    devServer: {
        port: 8080,
        allowedHosts: "all",
        client: {
            webSocketURL: 'ws://0.0.0.0:6103/ws',
        },
        headers: {
            'Access-Control-Allow-Origin': '*',
        }
    },
    transpileDependencies: true
})