const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {

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
      '/department': 'http://localhost:8090',
      '/annual': 'http://localhost:8090',
      '/member': 'http://localhost:8090',
    }
  },
  plugins: [
    new HtmlWebpackPlugin({
      filename : 'index.html',
      template: 'src/index.html',
      file: 'dist/index.html'
    }),
    new HtmlWebpackPlugin({
      filename : 'calendar.html',
      template: 'src/pages/calendar/calendar.html',
      file: 'dist/pages/calendar/calendar.html'
    }),
    new HtmlWebpackPlugin({
      filename : 'annualmanage.html',
      template: 'src/pages/admin/annualmanage.html',
      file: 'dist/pages/admin/annualmanage.html'
    }),
    new HtmlWebpackPlugin({
      filename : 'annualmodal.html',
      template: 'src/pages/admin/annualmodal.html',
      file: 'dist/pages/admin/annualmodal.html'
    })
      
  ]
};





