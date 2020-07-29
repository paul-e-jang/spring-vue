module.exports = {
  devServer: {
    port: 3000
  },
  configureWebpack: {
    entry: {
      app: './src/main.js',
      style: [
        'bootstrap/dist/css/bootstrap.min.css'
      ]
    }
  },
  transpileDependencies: [
    'vuetify'
  ]
}
