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
      filename : 'createMemberForm.html',
      template: 'src/pages/admin/member/createMemberForm.html',
      file: 'dist/pages/admin/member/createMemberForm.html'
    }),
    new HtmlWebpackPlugin({
      filename : 'memberList.html',
      template: 'src/pages/admin/member/memberList.html',
      file: 'dist/pages/admin/member/memberList.html'
    }),
    new HtmlWebpackPlugin({
      filename : 'adminTab.html',
      template: 'src/pages/admin/member/adminTab.html',
      file: 'dist/pages/admin/member/adminTab.html'
    })
  ]
};





