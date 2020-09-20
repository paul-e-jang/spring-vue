const path = require( 'path' );
const CKEditorWebpackPlugin = require( '@ckeditor/ckeditor5-dev-webpack-plugin' );
const { styles } = require( '@ckeditor/ckeditor5-dev-utils' );

module.exports = {
  parallel: false,
  transpileDependencies: [
    'vuetify',
    /ckeditor5-[^/\\]+[/\\]src[/\\].+\.js$/
  ],
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
    },
    plugins: [
      // CKEditor needs its own plugin to be built using webpack.
      new CKEditorWebpackPlugin( {
          // See https://ckeditor.com/docs/ckeditor5/latest/features/ui-language.html
          language: 'ko',

          // Append translations to the file matching the `app` name.
          translationsOutputFile: /app/
      } )
    ]
  },
  chainWebpack: config => {
    // (1.) To handle editor icons, get the default rule for *.svg files first:
    const svgRule = config.module.rule( 'svg' )

    // Then you can either:
    //
    // * clear all loaders for existing 'svg' rule:
    //
    //		svgRule.uses.clear();
    //
    // * or exclude ckeditor directory from node_modules:
    svgRule.exclude.add( path.join( __dirname, 'node_modules', '@ckeditor' ) )

    // Add an entry for *.svg files belonging to CKEditor. You can either:
    //
    // * modify the existing 'svg' rule:
    //
    //		svgRule.use( 'raw-loader' ).loader( 'raw-loader' );
    //
    // * or add a new one:
    config.module
        .rule( 'cke-svg' )
        .test( /ckeditor5-[^/\\]+[/\\]theme[/\\]icons[/\\][^/\\]+\.svg$/ )
        .use( 'raw-loader' )
        .loader( 'raw-loader' );

    // (2.) Transpile the .css files imported by the editor using PostCSS.
    // Make sure only the CSS belonging to ckeditor5-* packages is processed this way.
    config.module
        .rule( 'cke-css' )
        .test( /ckeditor5-[^/\\]+[/\\].+\.css$/ )
        .use( 'postcss-loader' )
        .loader( 'postcss-loader' )
        .tap( () => {
            return styles.getPostCssConfig( {
                themeImporter: {
                    themePath: require.resolve( '@ckeditor/ckeditor5-theme-lark' ),
                },
                minify: true
            } )
        } )
  }
}
