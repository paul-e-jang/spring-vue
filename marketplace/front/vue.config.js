module.exports = {
  devServer: {
    port: 3000
  },
  configureWebpack: {
    entry: {
      app: './src/main.js',
      style: []
    }
  },
  transpileDependencies: [
    'vuetify'
  ]
}
