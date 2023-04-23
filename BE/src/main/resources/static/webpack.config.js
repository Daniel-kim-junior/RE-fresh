const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  entry: {
    index : './src/index.js',
    calendar: './src/pages/calendar/Calendar.js'
  },
  output: {
    path : path.resolve(__dirname, 'dist'), // 경로
    filename : '[name].bundle.js', // 파일명
  },

  module: {
    rules: [
      {
        test: /\.css$/,
        use: ['postcss-loader']
      }
    ]
  },
  devServer: {
    static: 'dist',
    port: 4000,
    static: './',
    proxy: {
      '/calendar': 'http://localhost:8090',
    }
  },
  plugins: [
    new HtmlWebpackPlugin({
      filename : 'index.html',
      template: 'src/index.html',
      file: 'dist/index.html'
    }),
    new HtmlWebpackPlugin({
      filename : 'Calendar.html',
      template: 'src/pages/calendar/Calendar.html',
      file: 'dist/pages/calendar/Calendar.html'
    })
  ]
};





