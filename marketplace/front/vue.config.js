module.exports = {
  devServer: {
    port: 3000,
    proxy: {
      '/api/*': {
        target: 'http://localhost:8090'
      },
      '/rt/*': {
        target: 'http://localhost:8090'
      },
      '/local-file/*': {
        target: 'http://localhost:8090'
      }
    }
  },
  configureWebpack: {
    entry: {
      app: './src/main.js',
      style: [
        'vuetify/dist/vuetify.min.css'
      ]
    }
  },
  transpileDependencies: [
    'vuetify'
  ]
}
