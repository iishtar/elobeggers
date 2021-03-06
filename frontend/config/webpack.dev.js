var webpackMerge = require('webpack-merge');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var commonConfig = require('./webpack.common.js');
var helpers = require('./helpers');

module.exports = webpackMerge(commonConfig, {
  devtool: '#eval-source-map',

  output: {
    path: helpers.root('dist'),
    publicPath: 'http://localhost:8080/',
    filename: '[name]-[hash].js',
    chunkFilename: '[id].chunk.js'
  },
  debug: true,
  devtool: 'source-map',

  plugins: [
    new ExtractTextPlugin('[name].css'),
    // new webpack.optimize.UglifyJsPlugin()
  ],

  devServer: {
    historyApiFallback: true,
    colors: true,
    stats: 'minimal',
    contentBase: './src',
    proxy: {
      '/api': {
        target: 'http://localhost:4567',
        secure: false
      }
    }
  }
});
