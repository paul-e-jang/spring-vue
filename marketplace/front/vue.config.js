module.exports = {
  devServer: {
    port: 3000
  },
  configureWebpack: {
    entry: {
      app: './src/main.js'
    }
  },
  transpileDependencies: [
    'vuetify'
  ]
}
