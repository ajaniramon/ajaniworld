const webpack = require('webpack')

module.exports = {
  plugins: [
    new webpack.DefinePlugin({
      'process.env': {
        AJANIWORLD_API_BASEURL: JSON.stringify(process.env.AJANIWORLD_API_BASEURL)
      }
    })
  ]
}
